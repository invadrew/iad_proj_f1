package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopPlace;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopTransfer;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class RaceTimeMechanicController {

    @Autowired
    private RaceService raceService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PitStopPlaceService pitStopPlaceService;

    @Autowired
    private RaceRegistrationService raceRegistrationService;

    @Autowired
    private PitStopTransferService pitStopTransferService;

    @RequestMapping(value = "/raceTime-mechanic", method = RequestMethod.GET)
    public String toRace(ModelMap map, Authentication authentication, @Param("id") Integer id) {

        map.addAttribute("myPhoto", userService.findByLogin(authentication.getName()).getPhoto().getPath());
        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);
        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        map.addAttribute("team", team);

        Race race = raceService.findById(id);
        map.addAttribute("raceDateTime", race.getDateTime());
        Date now = new Date();

        RaceRegistration registration = raceRegistrationService.findById(team,race);

        if (race.getDateTime().after(now)) {
            map.addAttribute("ifStarted", false);
        } else {
            map.addAttribute("ifStarted", true);

            List<PitStopPlace> places = pitStopPlaceService.findAllByTeam(team);
            map.addAttribute("pitStopPlaces", places);

            Car firstCar = registration.getFirstCar();
            map.addAttribute("firstCar", firstCar);

            Car secondCar = registration.getSecondCar();
            map.addAttribute("secondCar", secondCar);

            try {
                map.addAttribute("fCarcase", firstCar.getCurrentCarcase());
                map.addAttribute("fChassis", firstCar.getCurrentChassis());
                map.addAttribute("fEngine", firstCar.getCurrentEngine());
                map.addAttribute("fElectronics", firstCar.getCurrentElectronics());

                map.addAttribute("sCarcase", secondCar.getCurrentCarcase());
                map.addAttribute("sChassis", secondCar.getCurrentChassis());
                map.addAttribute("sEngine", secondCar.getCurrentEngine());
                map.addAttribute("sElectronics", secondCar.getCurrentElectronics());

            } catch (NullPointerException x) {}

            List<PitStopTransfer> transfers = pitStopTransferService.findAllByTeamIdAndRace(team,race);
            map.addAttribute("transfers", transfers);

            List<PitStopPlace> placesFrom = new ArrayList<>();
            List<PitStopPlace> placesTo = new ArrayList<>();

            for(PitStopTransfer transfer: transfers) {
                placesFrom.add(transfer.getPlaceFrom());
                placesTo.add(transfer.getPlaceTo());
            }

            map.addAttribute("placesFrom", placesFrom);
            map.addAttribute("placesTo", placesTo);

        }

        return "RacetimeMechanicPage";

    }

    @RequestMapping(value ="/raceTime-mechanic/time", method = RequestMethod.GET)
    @ResponseBody
    public String getRaceTime() {
        List<Object[]> currRace = raceService.getCurrentEvent();
        Race race = raceService.findById((Integer) currRace.get(0)[6]);
        Date start = race.getDateTime();
        Date now = new Date();
        long current = start.getTime() - now.getTime();
        long diffSeconds = (-1)*current / 1000 % 60;
        long diffMinutes = (-1)*current / (60 * 1000) % 60;
        long diffHours = (-1) * current / (60 * 60 * 1000);
        return diffHours + ":" + diffMinutes + ":" + diffSeconds;
    }

    @RequestMapping(value = "/raceTime-mechanic/transfer", method = RequestMethod.POST)
    @ResponseBody
    public Object[] doTransfer(HttpServletRequest request, Authentication authentication) {

        Integer pitFrom = Integer.parseInt(request.getParameter("from"));
        Integer pitTo = Integer.parseInt(request.getParameter("to"));
        String item = request.getParameter("item");
        Float num = Float.parseFloat(request.getParameter("num"));
        User user = userService.findByLogin(authentication.getName());

        PitStopPlace placeFrom = pitStopPlaceService.findById(pitFrom);
        PitStopPlace placeTo = pitStopPlaceService.findById(pitTo);

        PitStopTransfer.Transfers transType = null;

        Object[] notEnough = {"not-enough"};

        switch (item) {
            case "tough":
                if (placeFrom.getTough() < num) return notEnough;
                transType = PitStopTransfer.Transfers.TOUGH;
                int tNum = Math.round(num);
                num = (float) Math.round(num);
                placeFrom.setTough(placeFrom.getTough() - tNum);
                placeTo.setTough(placeTo.getTough() + tNum);
                break;
            case "soft":
                if (placeFrom.getSoft() < num) return notEnough;
                transType = PitStopTransfer.Transfers.SOFT;
                int sNum =  Math.round(num);
                num = (float) Math.round(num);
                placeFrom.setSoft(placeFrom.getSoft() - sNum);
                placeTo.setTough(placeTo.getSoft() + sNum);
                break;
            case "fuel":
                if (placeFrom.getFuel() < num) return notEnough;
                transType = PitStopTransfer.Transfers.FUEL;
                placeFrom.setFuel(placeFrom.getFuel() - num);
                placeTo.setFuel(placeTo.getFuel() + num);
                break;
        }

        List<Object[]> currRace = raceService.getCurrentEvent();
        Race race = raceService.findById((Integer) currRace.get(0)[6]);

        Team team = teamMemberService.findByUserId(user.getId()).getTeam();

        try { TimeUnit.SECONDS.sleep(9); } catch (InterruptedException ex) { ex.printStackTrace(); }

        Date start = race.getDateTime();
        Date now = new Date();
        long current = start.getTime() - now.getTime();
        long diffSeconds = (-1)*current / 1000 % 60;
        long diffMinutes = (-1)*current / (60 * 1000) % 60;
        long diffHours = (-1) * current / (60 * 60 * 1000);
        String sec = "" + diffSeconds;
        if (diffSeconds < 10) { sec = "0" + diffSeconds; }
        String min = "" + diffMinutes;
        if (diffMinutes < 10) { min = "0" + diffMinutes; }
        String ho = "" + diffHours;
        if (diffHours < 10) { ho = "0" + diffHours; }

        PitStopTransfer transfer = new PitStopTransfer(race,placeFrom,placeTo, transType, num, AcceptStatus.ACCEPTED, team, LocalTime.parse(ho + ":" + min + ":" + sec));

        pitStopPlaceService.save(placeFrom);
        pitStopPlaceService.save(placeTo);
        pitStopTransferService.save(transfer);

        Object[] updData = {ho + ":" + min + ":" + sec, transfer.getAmount(), transfer.getTransfer().toString(), placeFrom.getName(), placeTo.getName(),
         placeFrom.getTough(), placeFrom.getSoft(), placeFrom.getFuel(), placeTo.getTough(), placeTo.getSoft(), placeTo.getFuel(), placeTo.getId(), placeFrom.getId()};

        return updData;


    }

}
