package com.rogo.inv.iadprojf1.controller;


import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}