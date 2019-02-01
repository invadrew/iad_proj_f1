package com.rogo.inv.iadprojf1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SponsorController {

    @RequestMapping(value = "/sponsor", method = RequestMethod.GET)
    public String toSponsorPage(ModelMap map) {
        return "SponsorProfilePage";
    }

}
