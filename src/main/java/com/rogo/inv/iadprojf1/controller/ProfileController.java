package com.rogo.inv.iadprojf1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ProfileController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String toProfile() {
        return "UserProfilePage";
    }
}