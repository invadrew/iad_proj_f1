package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.service.TeamMemberService;
import com.rogo.inv.iadprojf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RaceEndingController {


    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @RequestMapping(value = "/race-end", method = RequestMethod.GET)
    public String toEnd(ModelMap map, Authentication authentication) {

        map.addAttribute("myPhoto", userService.findByLogin(authentication.getName()).getPhoto().getPath());
        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);

        return "RaceEnding";

    }
}
