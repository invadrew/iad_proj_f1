package com.rogo.inv.iadprojf1.controller;


import com.rogo.inv.iadprojf1.entity.*;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopPlace;
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
import java.util.Date;
import java.util.List;

@Controller
public class RaceTimeRacerController {

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


        }

    return "RacetimeRacerPage";

    }


    @RequestMapping(value = "/raceTime-racer/offerPilotChange", method = RequestMethod.POST)
    @ResponseBody
    public void pilotChange(HttpServletRequest request, Authentication authentication) {

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

        PilotChange pilotChange = new PilotChange(race,pitStopPlace,car, AcceptStatus.ON_REVIEW, pilotTm, comment, LocalTime.parse(ho + ":" + min + ":" + sec));

        pilotChangeService.save(pilotChange);

    }


}
