package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Sponsoring;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
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

        map.addAttribute("myPhoto", userService.findByLogin(authentication.getName()).getPhoto().getPath());

        Sponsor sponsor;

        if (id != null) {
            sponsor = sponsorService.findByUserId(id);
        } else {
            sponsor = sponsorService.findByUserId(userService.findByLogin(authentication.getName()).getId());
        }

        map.addAttribute("spPhoto", userService.findById(sponsor.getUserId()).getPhoto().getPath());

        map.addAttribute("userName", userService.findById(sponsorService.findByUserId(sponsor.getUserId()).getUserId()).getLogin());
        map.addAttribute("sponsor", sponsor);
        try {
            map.addAttribute("teamCount", sponsoringService.getTeamCount(sponsor.getUserId()));
            map.addAttribute("sumMoney", sponsoringService.getSumMoney(sponsor.getUserId()));

        } catch (NullPointerException n) {

            map.addAttribute("teamCount", 0);
            map.addAttribute("sumMoney", 0);
        }

        List<Object[]> spTeams = sponsorService.getTeamsBySponsor(sponsor.getUserId());
        List<Object[]> sponsorings = new ArrayList<>();

        if (spTeams != null) {

            for (Object[] team : spTeams) {
                Object[] elements = {team[0], team[1], sponsoringService.getSumMoneyForTeam(sponsor.getUserId(), teamService.findById((Integer) team[1]).getId()),
                        sponsoringService.findAllByTeamAndSponsor(teamService.findById((Integer) team[1]),
                                sponsorService.findByUserId(sponsor.getUserId()))};
                sponsorings.add(elements);
            }
        }
        map.addAttribute("sponsorings", sponsorings);

        String name = "Панель администратора";

        if (userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.SPONSOR)) {
            name = sponsorService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName();
        }

        if (!(userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.SPONSOR)) && !(userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.ADMIN))) {
            name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                    teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        }
        map.addAttribute("name", name);

        return "SponsorProfilePage";
    }


    @RequestMapping(value = "/sponsor/sponse", method = RequestMethod.POST)
    @ResponseBody
    public void sponseTeam(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        Integer teamId = Integer.parseInt(request.getParameter("teamId"));
        Integer spId = Integer.parseInt(request.getParameter("spId"));
        Double money = Double.parseDouble(request.getParameter("money"));

        Date spDate = new Date();

        Sponsoring sponsoring = new Sponsoring(teamService.findById(teamId), sponsorService.findByUserId(spId), money, spDate);

        sponsoringService.save(sponsoring);

        Sponsor sponsor = sponsorService.findByUserId(spId);
        sponsor.setBudget(sponsor.getBudget() - money);
        sponsorService.save(sponsor);

        Team team = teamService.findById(teamId);
        teamService.updTeamBudget(team.getBudget() + money, teamId);
        team.setBudget(team.getBudget() + money);
        //teamService.save(team);

    }

    @RequestMapping(value = "/sponsor/new", method = RequestMethod.POST)
    @ResponseBody
    public Integer addTeam(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String teamName = request.getParameter("teamName");
        Integer spId = Integer.parseInt(request.getParameter("sponsor"));

        try { Team team = teamService.findByName(teamName);

        Sponsoring sponsoring = new Sponsoring(team, sponsorService.findByUserId(spId), 0, null);

        sponsoringService.save(sponsoring);

        return 1; } catch (Exception ex) {
            return 0;
        }
    }


}
