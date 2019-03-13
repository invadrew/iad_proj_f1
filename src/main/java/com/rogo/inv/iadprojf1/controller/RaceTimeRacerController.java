package com.rogo.inv.iadprojf1.controller;


import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;
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

}
