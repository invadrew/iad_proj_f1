package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.TeamMemberService;
import com.rogo.inv.iadprojf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RaceRegController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @RequestMapping(value = "/race-reg", method = RequestMethod.GET)
    public String toRaceRegPage(ModelMap map, Authentication authentication) {

        User user = userService.findByLogin(authentication.getName());

        String nameSurname = teamMemberService.findByUserId(user.getId()).getName() + " " +
                teamMemberService.findByUserId(user.getId()).getSurname();
        map.addAttribute("nameSurname",nameSurname);

        return "RaceRegistrationPage";
    }

}
