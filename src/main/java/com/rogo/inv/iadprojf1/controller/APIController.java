package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController {
    @Autowired
    private UserService userService;

    @Autowired
    private SponsorService sponsorService;


    @Autowired
    private SeasonService seasonService;

    @Autowired
    private WorldCupResultService worldCupResultService;

    @RequestMapping(value = "/querytest")
    public List<Object[]> viewTest() {
         return worldCupResultService.getResTable(2018);
    }

    /*@RequestMapping(value = "/users")
    public Iterable<User> viewUsersList() {
        return userService.findAll();
    }

    @RequestMapping(value = "/sponsors")
    public Iterable<Sponsor> viewSponsorsList() {
        return sponsorService.findAll();
    }

    @RequestMapping(value = "/seasons")
    public Iterable<Season> viewSeasonsList() {
        return seasonService.findAll();
    } */
}
