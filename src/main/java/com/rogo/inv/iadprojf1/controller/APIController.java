package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}