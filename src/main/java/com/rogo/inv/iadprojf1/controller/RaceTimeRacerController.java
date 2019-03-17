package com.rogo.inv.iadprojf1.controller;


import com.rogo.inv.iadprojf1.entity.*;
import com.rogo.inv.iadprojf1.entity.pitstop.*;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RaceTimeRacerController {

    @Autowired
    private PitStopTransferService pitStopTransferService;

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
    private CarService carService;

    @Autowired
    private PilotChangeService pilotChangeService;

    @Autowired
    private PitStopRepairService pitStopRepairService;

    @Autowired
    private PitStopServiceService pitStopServiceService;

    @RequestMapping(value = "/raceTime-racer", method = RequestMethod.GET)
    public String toRace(ModelMap map, Authentication authentication, @Param("id") Integer id) {

        map.addAttribute("myPhoto", userService.findByLogin(authentication.getName()).getPhoto().getPath());
        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);
        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        map.addAttribute("team", team);

        User user = userService.findByLogin(authentication.getName());
        TeamMember teamMember = teamMemberService.findByUserId(user.getId());

        Race race = raceService.findById(id);
        map.addAttribute("raceDateTime", race.getDateTime());
        Date now = new Date();

        map.addAttribute("isRegistrated", true);
        Car activeCar = null;

        RaceRegistration registration = raceRegistrationService.findById(team,race);
        if (registration.getFirstPilot().getUserId() == teamMember.getUserId()) {
            activeCar = registration.getFirstCar();
        } else {
            if (registration.getSecondPilot().getUserId() == teamMember.getUserId()) {
                activeCar = registration.getSecondCar();
            } else {
                map.addAttribute("isRegistrated", false);
            }
        }

        if (race.getDateTime().after(now)) {
            map.addAttribute("ifStarted", false);
        } else {
            map.addAttribute("ifStarted", true);
            List<PitStopPlace> places = pitStopPlaceService.findAllByTeam(team);
            map.addAttribute("pitStopPlaces", places);
            map.addAttribute("car", activeCar);
            try {
                map.addAttribute("carcase", activeCar.getCurrentCarcase());
                map.addAttribute("chassis", activeCar.getCurrentChassis());
                map.addAttribute("engine", activeCar.getCurrentEngine());
                map.addAttribute("electronics", activeCar.getCurrentElectronics());

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

            List<PilotChange> pilotChanges = pilotChangeService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.ON_REVIEW, team);
            List<TeamMember> pc_pilots = new ArrayList<>();
            List<Car> pc_cars = new ArrayList<>();
            for (PilotChange change: pilotChanges ) {
                pc_pilots.add(change.getPilot());
                pc_cars.add(change.getCar());
            }
            map.addAttribute("pilChang_review", pilotChanges);
            map.addAttribute("pilChang_review_cars", pc_cars);
            map.addAttribute("pilChang_review_pilots", pc_pilots);

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
            List<PitStopRepair> pitStopRepairsOnR = pitStopRepairService.findAllByRaceAndStatusAndTeamIdAndCar(race, AcceptStatus.ON_REVIEW, team, activeCar);
            List<PitStopRepair> pitStopRepairsR = pitStopRepairService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.REFUSED, team);

            List<PitStopRepair> repairsFromRacers = new ArrayList<>();

            List<Car> carsA = new ArrayList<>();
            List<Car> carsR = new ArrayList<>();

            for( PitStopRepair rep: pitStopRepairsOnR ) {
                if (rep.getSender().equals("MECHANIC")) {
                    repairsFromRacers.add(rep);
                }
            }

            for( PitStopRepair rep: pitStopRepairsA ) {
                carsA.add(rep.getCar());
            }

            for( PitStopRepair rep: pitStopRepairsR ) {
                carsR.add(rep.getCar());
            }

            map.addAttribute("repair_accept", pitStopRepairsA);
            map.addAttribute("repair_accept_cars", carsA);

            map.addAttribute("repair_refuse", pitStopRepairsR);
            map.addAttribute("repair_refuse_cars", carsR);

            map.addAttribute("repair_review", repairsFromRacers);

            List<PitStopService> service_acc = pitStopServiceService.findAllByTeamIdAndRaceAndStatus(team,race,AcceptStatus.ACCEPTED);
            List<PitStopService> service_ref = pitStopServiceService.findAllByTeamIdAndRaceAndStatus(team,race,AcceptStatus.REFUSED);

            List<Car> service_acc_cars = new ArrayList<>();
            List<PitStopPlace> service_acc_places = new ArrayList<>();

            for( PitStopService service: service_acc ) {
                service_acc_cars.add(service.getCar());
                service_acc_places.add(service.getPlace());
            }

            map.addAttribute("service_accept", service_acc);
            map.addAttribute("service_accept_cars", service_acc_cars);
            map.addAttribute("service_accept_places", service_acc_places);

            List<Car> service_ref_cars = new ArrayList<>();
            List<PitStopPlace> service_ref_places = new ArrayList<>();

            for( PitStopService service: service_ref ) {
                service_ref_cars.add(service.getCar());
                service_ref_places.add(service.getPlace());
            }

            map.addAttribute("service_refuse", service_ref);
            map.addAttribute("service_refuse_cars", service_ref_cars);
            map.addAttribute("service_refuse_places", service_ref_places);


        }

    return "RacetimeRacerPage";

    }


    @RequestMapping(value = "/raceTime-racer/offerPilotChange", method = RequestMethod.POST)
    @ResponseBody
    public String pilotChange(HttpServletRequest request, Authentication authentication) {

        String comment = request.getParameter("comment");
        PitStopPlace pitStopPlace = pitStopPlaceService.findById(Integer.parseInt(request.getParameter("place")));

        User pilot = userService.findByLogin(authentication.getName());
        TeamMember pilotTm = teamMemberService.findByUserId(pilot.getId());
        Team team = teamMemberService.findByUserId(pilot.getId()).getTeam();
        List<Object[]> currRace = raceService.getCurrentEvent();
        Race race = raceService.findById((Integer) currRace.get(0)[6]);

        Car car;

        RaceRegistration registration = raceRegistrationService.findById(team,race);
        if (registration.getFirstPilot().getUserId() == pilot.getId()) {
            car = carService.findById(registration.getFirstCar().getId());
        } else {
            car = carService.findById(registration.getSecondCar().getId());
        }

        List<TeamMember> allPilots = teamMemberService.getAllspecificType(team.getId(), "RACER");
        List<TeamMember> freePilots = new ArrayList<>();

        for (TeamMember member: allPilots) {
            if ((member.getUserId() != registration.getFirstPilot().getUserId()) && (member.getUserId() != registration.getSecondPilot().getUserId())) {
                freePilots.add(member);
            }
        }

        if (freePilots.isEmpty()) { return "no-free"; }

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

        PilotChange pilotChange = new PilotChange(race,pitStopPlace,car, AcceptStatus.ON_REVIEW, pilotTm, comment, LocalTime.parse(ho + ":" + min + ":" + sec), team);

        pilotChangeService.save(pilotChange);

        return "ok";

    }

    @RequestMapping(value = "/raceTime-racer/news", method = RequestMethod.GET)
    @ResponseBody
    public Object getNews(@RequestParam(name = "lastTime", required = false) String lTime, Authentication authentication, HttpServletResponse response) {

            String[] lTimeParts = lTime.trim().split(":");
            if (Integer.parseInt(lTimeParts[0]) < 10 && ((!lTimeParts[0].startsWith("0") || lTimeParts[0].length() == 1))) {
                lTimeParts[0] = "0" + lTimeParts[0];
                if (lTimeParts[0].equals("0")) lTimeParts[0] = "00";
            }
            if (Integer.parseInt(lTimeParts[1]) < 10 && ((!lTimeParts[1].startsWith("0") || lTimeParts[1].length() == 1))) {
                lTimeParts[1] = "0" + lTimeParts[1];
                if (lTimeParts[1].equals("0")) lTimeParts[1] = "00";
            }
            if (Integer.parseInt(lTimeParts[2]) < 10 && ((!lTimeParts[2].startsWith("0") || lTimeParts[2].length() == 1))) {
                lTimeParts[2] = "0" + lTimeParts[2];
                if (lTimeParts[2].equals("0")) lTimeParts[2] = "00";
            }
            LocalTime maxTime = LocalTime.parse(lTimeParts[0] + ":" + lTimeParts[1] + ":" + lTimeParts[2]);


            List<Object[]> currRace = raceService.getCurrentEvent();
            Race race = raceService.findById((Integer) currRace.get(0)[6]);
            Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();

            Date start = race.getDateTime();
            Date now = new Date();
            long current = start.getTime() - now.getTime();
            long diffSeconds = (-1) * current / 1000 % 60;
            long diffMinutes = (-1) * current / (60 * 1000) % 60;
            long diffHours = (-1) * current / (60 * 60 * 1000);
            String sec = "" + diffSeconds;
            if (diffSeconds < 10) {
                sec = "0" + diffSeconds;
            }
            String min = "" + diffMinutes;
            if (diffMinutes < 10) {
                min = "0" + diffMinutes;
            }
            String ho = "" + diffHours;
            if (diffHours < 10) {
                ho = "0" + diffHours;
            }

            List<PitStopTransfer> transfers = pitStopTransferService.findAllByTeamIdAndRaceOrderByTimeDesc(team, race);
            List<PitStopRepair> repairs = pitStopRepairService.findAllByRaceAndTeamId(race,team);
            List<PitStopRepair> acc_repairs = pitStopRepairService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.ACCEPTED, team);
            List<PilotChange> pilotChangesA = pilotChangeService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.ACCEPTED, team);
            List<PilotChange> pilotChangesR = pilotChangeService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.REFUSED, team);
        List<PilotChange> pilotChangesRev = pilotChangeService.findAllByRaceAndStatusAndTeamId(race, AcceptStatus.ON_REVIEW, team);
            LocalTime curr = LocalTime.parse(ho + ":" + min + ":" + sec);

            for (PitStopTransfer transfer : transfers) {

                if (transfer.getTime().isBefore(curr) && (transfer.getTime().isAfter(maxTime))) {
                    return ho + ":" + min + ":" + sec;
                }
            }

            for (PilotChange change: pilotChangesA) {
                if (change.getTime().isBefore(curr) && (change.getTime().isAfter(maxTime))) {
                    return ho + ":" + min + ":" + sec;
                }
            }

        for (PilotChange change: pilotChangesR) {
            if (change.getTime().isBefore(curr) && (change.getTime().isAfter(maxTime))) {
                return ho + ":" + min + ":" + sec;
            }
        }

        for (PilotChange change: pilotChangesRev) {
            if (change.getTime().isBefore(curr) && (change.getTime().isAfter(maxTime))) {
                return ho + ":" + min + ":" + sec;
            }
        }

        for (PitStopRepair rep: acc_repairs) {
            if (rep.getTime().isBefore(curr) && (rep.getTime().isAfter(maxTime))) {
                return "/garage?id=" + rep.getCar().getId();
            }
        }

        for(PitStopRepair rep: repairs) {
            if (rep.getTime().isBefore(curr) && (rep.getTime().isAfter(maxTime))) {

                return ho + ":" + min + ":" + sec;
            }
        }

            return "nothing";
        }


        @RequestMapping(value = "/raceTime-racer/askRepair", method = RequestMethod.POST)
        @ResponseBody
        public void askRepair(Authentication authentication, HttpServletRequest request) {

        String comment = request.getParameter("comment");

            User pilot = userService.findByLogin(authentication.getName());
            TeamMember pilotTm = teamMemberService.findByUserId(pilot.getId());
            Team team = teamMemberService.findByUserId(pilot.getId()).getTeam();
            List<Object[]> currRace = raceService.getCurrentEvent();
            Race race = raceService.findById((Integer) currRace.get(0)[6]);

            Car car;

            RaceRegistration registration = raceRegistrationService.findById(team,race);
            if (registration.getFirstPilot().getUserId() == pilot.getId()) {
                car = carService.findById(registration.getFirstCar().getId());
            } else {
                car = carService.findById(registration.getSecondCar().getId());
            }

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

            PitStopRepair repair = new PitStopRepair(race,null,car,AcceptStatus.ON_REVIEW,comment,"RACER",team,
                    LocalTime.parse(ho + ":" + min + ":" + sec));

            pitStopRepairService.save(repair);

        }


        @RequestMapping(value = "/raceTime-racer/conformRepair", method = RequestMethod.POST)
        @ResponseBody
        public void confirmRepair(HttpServletRequest request, Authentication authentication) {

            String comment = request.getParameter("comment");
            PitStopRepair pitStopRepair= pitStopRepairService.findById(Integer.parseInt(request.getParameter("id")));
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));

            Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
            List<Object[]> currRace = raceService.getCurrentEvent();
            Race race = raceService.findById((Integer) currRace.get(0)[6]);

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

            if (status) {
                pitStopRepair.setStatus(AcceptStatus.ACCEPTED);
                pitStopRepairService.save(pitStopRepair);
            } else {
                pitStopRepair.setStatus(AcceptStatus.REFUSED);
                pitStopRepairService.save(pitStopRepair);
            }

        }

        @RequestMapping(value = "/raceTime-racer/askService", method = RequestMethod.POST)
        @ResponseBody
        public String askService(HttpServletRequest request, Authentication authentication) {

            String comment = request.getParameter("comment");
            PitStopPlace place = pitStopPlaceService.findById(Integer.parseInt(request.getParameter("place")));
            Float fuel = Float.parseFloat(request.getParameter("fuel"));
            String tires = request.getParameter("tires");

            User pilot = userService.findByLogin(authentication.getName());
            TeamMember pilotTm = teamMemberService.findByUserId(pilot.getId());
            Team team = teamMemberService.findByUserId(pilot.getId()).getTeam();
            List<Object[]> currRace = raceService.getCurrentEvent();
            Race race = raceService.findById((Integer) currRace.get(0)[6]);

            Car car;

            RaceRegistration registration = raceRegistrationService.findById(team,race);
            if (registration.getFirstPilot().getUserId() == pilot.getId()) {
                car = carService.findById(registration.getFirstCar().getId());
            } else {
                car = carService.findById(registration.getSecondCar().getId());
            }

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

            PitStopService.TireTypes tireTypes = null;

            if(tires.equals("SOFT")) tireTypes = PitStopService.TireTypes.SOFT;
            if(tires.equals("TOUGH")) tireTypes = PitStopService.TireTypes.TOUGH;

            PitStopService pitStopService = new PitStopService(race,place,car,null,fuel,tireTypes,AcceptStatus.ON_REVIEW,comment, LocalTime.parse(ho + ":" + min + ":" + sec),
                    team,"RACER");

            pitStopServiceService.save(pitStopService);

            return "ok";

        }

    }

