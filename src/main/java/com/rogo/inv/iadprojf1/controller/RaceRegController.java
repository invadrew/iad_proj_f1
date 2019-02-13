package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RaceRegController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private RaceRegistrationService raceRegistrationService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/race-reg", method = RequestMethod.GET)
    public String toRaceRegPage(ModelMap map, Authentication authentication) {

        User user = userService.findByLogin(authentication.getName());

        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);

        List<Object[]> currRace = raceService.getCurrentEvent();
        map.addAttribute("currRace", currRace);

        List<Object[]> regTable = raceRegistrationService.getRegistrationTable(raceService.findTopByOrderByDateTimeDesc().getId());
        map.addAttribute("regTable", regTable);

        Team team = teamService.findById(teamMemberService.findByUserId(user.getId()).getTeam().getId());

        map.addAttribute("team", team);

        List<Object[]> teamMembers = teamMemberService.getAllRacers(team.getId());

        map.addAttribute("racers", teamMembers);

        List<Car> cars = carService.findAllByTeam(team);
        map.addAttribute("cars", cars);

        return "RaceRegistrationPage";
    }

}
