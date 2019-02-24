package com.rogo.inv.iadprojf1.tgbot;

import com.rogo.inv.iadprojf1.service.WorldCupResultService;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.asciithemes.a7.A7_Grids;;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.table.TableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:private.properties")
public class TGBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String BOT_USERNAME;

    @Value("${telegram.bot.token}")
    private String TOKEN;

    @Autowired
    private WorldCupResultService worldCupResultService;

    @Override
    public void onUpdateReceived(Update update) {
        String outString = printWorldCupRating(2017, 0, 10);
        if (update.hasMessage()) {
            SendMessage message = new SendMessage()
                    .enableMarkdown(true)
                    .setParseMode("HTML")
                    .setChatId(update.getMessage().getChatId())
                    .setText(outString);
            try {
                execute(message); // Call method to send the message
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

    public List<Object[]> getTopTeams(int season, int from, int to) {
        return worldCupResultService.getResTable(season).stream()
                .sorted((o1, o2) -> (Integer) o1[0] - (Integer) o2[0])
                .filter(o -> ((Integer) o[0]) < to)
                .filter(o -> ((Integer) o[0]) >= from)
                .collect(Collectors.toList());
    }

    public String printWorldCupRating(int season, int from, int to) {
        Table table = new Table();
        table.setAlignments(Arrays.asList(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT));

        List<TableRow> rows = new ArrayList<>();

        TableRow<String> header = new TableRow<>();
        header.setColumns(Arrays.asList("Place", "Pilot Surname", "Pilot Name", "Team"));

        rows.add(header);

        getTopTeams(season, from, to).forEach(o -> {
            TableRow<String> row = new TableRow<>();
            row.setColumns(Arrays.asList(o[0].toString(), o[1].toString(), o[2].toString(), o[3].toString()));
            rows.add(row);
        });

        table.setRows(rows);

        System.out.println(table.serialize());
        return table.serialize();
    }
}
