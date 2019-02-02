package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
public class RaceArchiveController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private ChampionshipService championshipService;

    @Autowired
    private RaceResultService raceResultService;

    @RequestMapping(value = "/race-res", method = RequestMethod.GET)
    public String toRaceResPage(ModelMap map, Authentication authentication) {

        User user = userService.findByLogin(authentication.getName());

        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);

        map.addAttribute("seasonsList",seasonService.findAll());

        return "RaceResultsPage";
    }

    @RequestMapping(value = "/race-res/champs", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> getChamps(@RequestParam Integer year) {

        List<Object> list = new LinkedList<>();
        List<Championship> champs = championshipService.getAllBySeason(seasonService.findByYear(year));
        for (int i =0; i<champs.size(); i++) {
            list.add(champs.get(i).getName());
        }

        return list;
    }

    @RequestMapping(value = "/race-res/data", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getWorldData(@RequestParam Integer year, @RequestParam String name) {
        return raceResultService.getResultTable(year,name);
    }

}
