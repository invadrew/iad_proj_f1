package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.CarService;
import com.rogo.inv.iadprojf1.service.TeamMemberService;
import com.rogo.inv.iadprojf1.service.TeamService;
import com.rogo.inv.iadprojf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GarageController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/garage", method = RequestMethod.GET)
    public String toGarage(ModelMap map, Authentication authentication) {

        User user = userService.findByLogin(authentication.getName());
        Team team = teamMemberService.findByUserId(user.getId()).getTeam();

        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);
        map.addAttribute("team", team);

        List<Car> cars = carService.findAllByTeam(team);

        map.addAttribute("cars", cars);

        return "GaragePage";
    }
}
