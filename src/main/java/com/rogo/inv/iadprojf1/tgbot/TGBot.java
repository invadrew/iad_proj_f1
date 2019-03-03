package com.rogo.inv.iadprojf1.tgbot;

import com.rogo.inv.iadprojf1.service.ConstrCupResultService;
import com.rogo.inv.iadprojf1.service.RaceService;
import com.rogo.inv.iadprojf1.service.WorldCupResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:private.properties")
public class TGBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String BOT_USERNAME;

    @Value("${telegram.bot.token}")
    private String TOKEN;

    /* ================================
     Output constants
    ================================ */

    private final static String HELP_INFO = "Команды бота\n" +
            "/worldcup <сезон> <<место_от> <место_до>>- вывод таблицы лидеров кубка мира в данном сезоне\n" +
            "/constrcup <сезон> <<место_от> <место_до>>- вывод таблицы лидеров кубка конструкторов в данном сезоне\n" +
            "/schedule - вывод ближайшей гонки\n" +
            "/help команды бота\n";

    private final static String START_INFO = "Добро пожаловать в нашего бота!\n" +
            "При помощи него вы можете получить актуальную информацию о ближайщих событиях и рейтинге команд\n" +
            HELP_INFO;

    private final static String UNKNOWN_INFO = "Неизвестная команда, введите /help для вывода возможных команд.";

    /* ================================
     Services decloration
    ================================ */

    @Autowired
    private WorldCupResultService worldCupResultService;

    @Autowired
    private ConstrCupResultService constrCupResultService;

    @Autowired
    private RaceService raceService;

    /* ================================
     Overrided methods from TelegramLongPollingBot
    ================================ */

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String outStr;
            String mess =  update.getMessage().getText().replaceAll(" +", " ");
            String[] tokens = mess.split(" ");
            switch (tokens[0]) {
                case "/start":
                    outStr = START_INFO;
                    break;
                case "/help":
                    outStr = HELP_INFO;
                    break;
                case "/worldcup":
                    if (tokens.length > 3) {
                        outStr = printWorldCupRating(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                    } else if (tokens.length > 1) {
                        outStr = printWorldCupRating(Integer.parseInt(tokens[1]), 0, 10);
                    } else {
                        outStr = printWorldCupRating(Calendar.getInstance().get(Calendar.YEAR), 0, 10);
                    }
                    break;
                case "/constrcup":
                    if (tokens.length > 3) {
                        outStr = printConstrCupRating(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                    } else if (tokens.length > 1) {
                        outStr = printConstrCupRating(Integer.parseInt(tokens[1]), 0, 10);
                    } else {
                        outStr = printConstrCupRating(Calendar.getInstance().get(Calendar.YEAR), 0, 10);
                    }
                    break;
                case "/schedule":
                    outStr = printCurrentEvent();
                    break;
                default:
                    outStr = UNKNOWN_INFO;
            }

            SendMessage message = new SendMessage()
                    .enableMarkdown(true)
                    .setChatId(update.getMessage().getChatId())
                    .setText(outStr);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    /* ================================
     fetch data from DB
    ================================ */

    private List<Object[]> getTopTeams(int season, int from, int to) {
        return worldCupResultService.getResTable(season).stream()
                .sorted((o1, o2) -> (Integer) o1[0] - (Integer) o2[0])
                .filter(o -> ((Integer) o[0]) < to)
                .filter(o -> ((Integer) o[0]) >= from)
                .collect(Collectors.toList());
    }

    private List<Object[]> getTopConstr(int season, int from, int to) {
        return constrCupResultService.getConstrCupResultTable(season).stream()
                .sorted((o1, o2) -> (Integer) o1[0] - (Integer) o2[0])
                .filter(o -> ((Integer) o[0]) < to)
                .filter(o -> ((Integer) o[0]) >= from)
                .collect(Collectors.toList());
    }

    /* ================================
     formatted output
    ================================ */

    private String printWorldCupRating(int season, int from, int to) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Результаты кубка мира ").append(season).append(" года.\n");
        stringBuilder.append("Место | Фамилия пилота | Имя пилота | Команда\n");
        stringBuilder.append(getTopTeams(season, from, to).stream()
                .map(objects ->
                        Arrays.stream(objects)
                                .limit(4)
                                .map(Object::toString)
                                .reduce((s, s2) -> s + ", " + s2).orElse("")
                )
                .reduce((s, s2) -> s + "\n" + s2).orElse("")
        );
        return stringBuilder.toString();
    }

    private String printConstrCupRating(int season, int from, int to) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Результаты кубка конструкторов ").append(season).append(" года.\n");
        stringBuilder.append("Место | Команда\n");
        stringBuilder.append(getTopConstr(season, from, to).stream()
                .map(objects ->
                        Arrays.stream(objects)
                                .limit(2)
                                .map(Object::toString)
                                .reduce((s, s2) -> s + ", " + s2).orElse("")
                )
                .reduce((s, s2) -> s + "\n" + s2).orElse("")
        );
        return stringBuilder.toString();
    }

    private String printCurrentEvent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Место | Фамилия пилота | Имя пилота | Команда\n");
        stringBuilder.append(Arrays.stream(raceService.getCurrentEvent())
                .map(Object::toString)
                .reduce((s, s2) -> s + ", " + s2).orElse("")
        );
        return stringBuilder.toString();
    }
}
