package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class APIController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private SponsorService sponsorService;

//    @Autowired
//    private TeamService teamService;

    @RequestMapping(value = "/users")
    public Iterable<User> viewUsersList() {
        return userService.findAll();
    }

//    @RequestMapping(value = "/sponsors")
//    public Iterable<Sponsor> viewSponsorsList() {
//        return sponsorService.findAll();
//    }

//    @RequestMapping(value = "/teams")
//    public Iterable<Team> viewTeamsList() {
//        return teamService.findAll();
//    }


}
