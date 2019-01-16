package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
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
    private RaceRegistrationService raceRegistrationService;


    @Autowired
    private ConstrCupResultService constrCupResultService;

    @Autowired
    private RaceResultService raceResultService;


    @RequestMapping(value = "/querytest")
    public List<Object[]> viewTest() {
        // return worldCupResultService.getResTable(2018);
       // return constrCupResultService.getConstrCupResultTable(2018);
        //return raceResultService.getResultTable(2017,"Gran-Pri Russia");
        return raceRegistrationService.getRegistrationTable(1);
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
