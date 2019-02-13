package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.service.ConstrCupResultService;
import com.rogo.inv.iadprojf1.service.RaceResultService;
import com.rogo.inv.iadprojf1.service.RaceService;
import com.rogo.inv.iadprojf1.service.SponsoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private ConstrCupResultService constrCupResultService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private RaceResultService raceResultService;

    @Autowired
    private SponsoringService sponsoringService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toMain(ModelMap map) {
        List<Object[]> mainPageResTable = constrCupResultService.getRates();
        List<Object[]> currentEvent = raceService.getCurrentEvent();
        Object[] newSponsNews = sponsoringService.getLatestSponsNews();
        Object[] newRaceNews = raceResultService.getRaceNews();
        map.addAttribute("mainPageResTable", mainPageResTable);
        map.addAttribute("currentEvent",currentEvent);
        map.addAttribute("newSponsNews",newSponsNews);
        map.addAttribute("newRaceNews",newRaceNews);
        return "MainPage";
    }
}