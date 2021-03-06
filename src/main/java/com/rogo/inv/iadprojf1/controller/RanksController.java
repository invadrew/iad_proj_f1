package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RanksController {

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private ConstrCupResultService constrCupResultService;

    @Autowired
    private WorldCupResultService worldCupResultService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private SponsorService sponsorService;

    @RequestMapping(value = "/ranks", method = RequestMethod.GET)
    public String toRanks(ModelMap map, Authentication authentication) {

        map.addAttribute("myPhoto", userService.findByLogin(authentication.getName()).getPhoto().getPath());

        User user = userService.findByLogin(authentication.getName());

        String name = "Панель администратора";

        if (userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.SPONSOR)) {
            name = sponsorService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName();
        }

        if (!(userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.SPONSOR)) && !(userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.ADMIN))) {
            name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                    teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        }
        map.addAttribute("name", name);

        List<Season> seasonsList= seasonService.findAll();
        map.addAttribute("seasonsList",seasonsList);
        return "RanksPage";
    }

    @RequestMapping(value = "/ranks/world", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getWorldData(@RequestParam Integer year) {
        return worldCupResultService.getResTable(year);
    }

    @RequestMapping(value = "/ranks/constr", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getConstrData(@RequestParam Integer year) {
        return constrCupResultService.getConstrCupResultTable(year);
    }

}
