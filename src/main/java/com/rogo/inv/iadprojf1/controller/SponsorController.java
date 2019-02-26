package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SponsorController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private SponsoringService sponsoringService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/sponsor", method = RequestMethod.GET)
    public String toSponsorPage(ModelMap map, Authentication authentication, @Param("id") Integer id) {

        Sponsor sponsor;

        if (id != null) {
            sponsor = sponsorService.findByUserId(id);
        } else {
            sponsor = sponsorService.findByUserId(userService.findByLogin(authentication.getName()).getId());
        }

        map.addAttribute("sponsor", sponsor);
        map.addAttribute("teamCount", sponsoringService.getTeamCount(sponsor.getUserId()));
        map.addAttribute("sumMoney", sponsoringService.getSumMoney(sponsor.getUserId()));

        List<Object[]> spTeams = sponsorService.getTeamsBySponsor(sponsor.getUserId());
        List<Object[]> sponsorings = new ArrayList<>();

        for (Object[] team: spTeams) {
            Object[] elements = { team[0], team[1] , sponsoringService.findAllByTeamAndSponsor(teamService.findById( (Integer) team[1]),
                    sponsorService.findByUserId(sponsor.getUserId())) };
            sponsorings.add(elements);
        }

        map.addAttribute("sponsorings", sponsorings);

        try { String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name); }

        catch (NullPointerException e ) {
            String name = sponsorService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName();
            map.addAttribute("name", name);
        }

        return "SponsorProfilePage";
    }

}
