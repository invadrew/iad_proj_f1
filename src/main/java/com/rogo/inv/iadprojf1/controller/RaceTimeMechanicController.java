package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

        if (race.getDateTime().after(now)) {
            map.addAttribute("ifStarted", false);
        } else {
            map.addAttribute("ifStarted", true);
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


}
