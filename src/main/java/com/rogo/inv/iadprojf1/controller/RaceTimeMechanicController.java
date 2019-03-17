package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.*;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopPlace;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    private PilotChangeService pilotChangeService;

    @Autowired
    private PitStopRepairService pitStopRepairService;

    @Autowired
    private CarService carService;

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

            List<TeamMember> allPilots = teamMemberService.getAllspecificType(team.getId(), "RACER");
            List<TeamMember> freePilots = new ArrayList<>();

            for (TeamMember member: allPilots) {
                if ((member.getUserId() != registration.getFirstPilot().getUserId()) && (member.getUserId() != registration.getSecondPilot().getUserId())) {
                    freePilots.add(member);
                }
            }

            map.addAttribute("freePilots", freePilots);

            List<PilotChange> pilotChanges = pilotChangeService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.ON_REVIEW, team);
            List<TeamMember> pc_pilots = new ArrayList<>();
            List<Car> pc_cars = new ArrayList<>();
            List<PitStopPlace> placeList = new ArrayList<>();
            for (PilotChange change: pilotChanges ) {
                pc_pilots.add(change.getPilot());
                pc_cars.add(change.getCar());
                placeList.add(change.getPlace());
            }
            map.addAttribute("pilChang_review", pilotChanges);
            map.addAttribute("pilChang_review_cars", pc_cars);
            map.addAttribute("pilChang_review_pilots", pc_pilots);
            map.addAttribute("pilChang_review_places", placeList);

            List<PilotChange> pilotChangesAcc = pilotChangeService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.ACCEPTED, team);
            List<TeamMember> pc_pilots_acc = new ArrayList<>();
            List<Car> pc_cars_acc = new ArrayList<>();
            for (PilotChange change: pilotChangesAcc ) {
                pc_pilots_acc.add(change.getPilot());
                pc_cars_acc.add(change.getCar());
            }
            map.addAttribute("pilChang_accept", pilotChangesAcc);
            map.addAttribute("pilChang_accept_cars", pc_cars_acc);
            map.addAttribute("pilChang_accept_pilots", pc_pilots_acc);

            List<PilotChange> pilotChangesRef = pilotChangeService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.REFUSED, team);
            List<TeamMember> pc_pilots_ref = new ArrayList<>();
            List<Car> pc_cars_ref = new ArrayList<>();
            for (PilotChange change: pilotChangesRef ) {
                pc_pilots_ref.add(change.getPilot());
                pc_cars_ref.add(change.getCar());
            }
            map.addAttribute("pilChang_refuse", pilotChangesRef);
            map.addAttribute("pilChang_refuse_cars", pc_cars_ref);
            map.addAttribute("pilChang_refuse_pilots", pc_pilots_ref);

            List<PitStopRepair> pitStopRepairsA = pitStopRepairService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.ACCEPTED, team);
            List<PitStopRepair> pitStopRepairsOnR = pitStopRepairService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.ON_REVIEW, team);
            List<PitStopRepair> pitStopRepairsR = pitStopRepairService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.REFUSED, team);

            List<PitStopRepair> repairsFromRacers = new ArrayList<>();

            List<Car> carsA = new ArrayList<>();
            List<Car> carsR = new ArrayList<>();
            List<Car> carsOnR = new ArrayList<>();

            for( PitStopRepair rep: pitStopRepairsOnR ) {
                if (rep.getSender().equals("RACER")) {
                    repairsFromRacers.add(rep);
                }
            }

            for( PitStopRepair rep: pitStopRepairsA ) {
                carsA.add(rep.getCar());
            }

            for( PitStopRepair rep: pitStopRepairsR ) {
                carsR.add(rep.getCar());
            }

            for( PitStopRepair rep: repairsFromRacers ) {
                carsOnR.add(rep.getCar());
            }

            map.addAttribute("repair_accept", pitStopRepairsA);
            map.addAttribute("repair_accept_cars", carsA);

            map.addAttribute("repair_refuse", pitStopRepairsR);
            map.addAttribute("repair_refuse_cars", carsR);

            map.addAttribute("repair_review", repairsFromRacers);
            map.addAttribute("repair_review_cars", carsOnR);

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

    @RequestMapping(value = "/raceTime-mechanic/pilotChange", method = RequestMethod.POST)
    @ResponseBody
    public void changePilot(Authentication authentication, HttpServletRequest request) {

        String comment = request.getParameter("comment");
        TeamMember pilot = teamMemberService.findByUserId(Integer.parseInt(request.getParameter("pilot")));
        PilotChange pilotChange = pilotChangeService.findById(Integer.parseInt(request.getParameter("id")));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        List<Object[]> currRace = raceService.getCurrentEvent();
        Race race = raceService.findById((Integer) currRace.get(0)[6]);
        RaceRegistration registration = raceRegistrationService.findById(team, race);

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

        pilotChange.setComment(comment);
        pilotChange.setTime(LocalTime.parse(ho + ":" + min + ":" + sec));

        if (status) {

            pilotChange.setStatus(AcceptStatus.ACCEPTED);
            if (pilotChange.getPilot().getUserId() == registration.getFirstPilot().getUserId()) {
                registration.setFirstPilot(pilot);
            } else {
                registration.setSecondPilot(pilot);
            }

            raceRegistrationService.save(registration);

        } else {

            pilotChange.setStatus(AcceptStatus.REFUSED);
        }

        pilotChangeService.save(pilotChange);

    }

    @RequestMapping(value = "/raceTime-mechanic/repair", method = RequestMethod.POST)
    @ResponseBody
    public void repair(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {

        String comment = request.getParameter("comment");
        PitStopRepair pitStopRepair = pitStopRepairService.findById(Integer.parseInt(request.getParameter("id")));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        List<Object[]> currRace = raceService.getCurrentEvent();
        Race race = raceService.findById((Integer) currRace.get(0)[6]);
        RaceRegistration registration = raceRegistrationService.findById(team, race);

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

        pitStopRepair.setComment(comment);
        pitStopRepair.setTime(LocalTime.parse(ho + ":" + min + ":" + sec));

        if (!status) {

            pitStopRepair.setStatus(AcceptStatus.REFUSED);
            pitStopRepairService.save(pitStopRepair);
         //   try { response.sendRedirect("/garage?id=" + pitStopRepair.getCar().getId()); } catch (IOException x) { }

        } else {

            pitStopRepair.setStatus(AcceptStatus.ACCEPTED);
            pitStopRepairService.save(pitStopRepair);
        }

    }

    @RequestMapping(value = "/raceTime-mechanic/offer-repair", method = RequestMethod.POST)
    @ResponseBody
    public void offerRepair(HttpServletRequest request, Authentication authentication) {

        String comment = request.getParameter("comment");

        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        List<Object[]> currRace = raceService.getCurrentEvent();
        Race race = raceService.findById((Integer) currRace.get(0)[6]);
        Car car = carService.findById(Integer.parseInt(request.getParameter("id")));

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

        PitStopRepair repair = new PitStopRepair(race,null,car,AcceptStatus.ON_REVIEW,comment,"MECHANIC",team,
                LocalTime.parse(ho + ":" + min + ":" + sec));

        pitStopRepairService.save(repair);


    }

}
