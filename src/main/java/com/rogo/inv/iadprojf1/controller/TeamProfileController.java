package com.rogo.inv.iadprojf1.controller;

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

import java.util.List;

import static com.rogo.inv.iadprojf1.entity.User.Spec;


@Controller
public class TeamProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private ConstrCupResultService constrCupResultService;

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public String toTeamProfile(ModelMap map, Authentication authentication, @Param("id") Integer id) {

        User user = userService.findByLogin(authentication.getName());

        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);

        Team team;

        if (id == null) {
             team = teamMemberService.findByUserId(user.getId()).getTeam();
        } else {
            team = teamService.findById(id);
        }

        String nameSurname = teamMemberService.findByUserId(user.getId()).getName() + " " +
                teamMemberService.findByUserId(user.getId()).getSurname();
        map.addAttribute("nameSurname",nameSurname);

        map.addAttribute("team", team);

        Object gSP = teamService.getSeasPoints(seasonService.findTopByOrderByYearDesc().getYear(), team.getId());
        if (gSP == null) {gSP = "ещё нет данных за сезон";}
        map.addAttribute("seasonPoints", gSP);

        Object gBP = teamService.getBestPlace(team.getId());
        if (gBP == null) {gBP = "ещё нет кубков";}
        map.addAttribute("bestPlace",gBP);

        Object racersCount = teamService.getRacersCount(team.getId());
        if (racersCount == null) {racersCount = "ни одного гонщика";}
        map.addAttribute("racers",racersCount);

        Object mechanicCount = teamService.getMechanicsCount(team.getId());
        if (mechanicCount == null) {mechanicCount = "ни одного механика";}
        map.addAttribute("mechanics",mechanicCount);

        Object constrCount = teamService.getConstrsCount(team.getId());
        if (constrCount == null) {constrCount = "ни одного конструктора";}
        map.addAttribute("constrs",constrCount);

        Object manCount = teamService.getManagersCount(team.getId());
        if (manCount == null) {manCount = "ни одного менеджера";}
        map.addAttribute("managers",manCount);

       map.addAttribute("bestRacer",teamService.bestRacer(team.getId()));

       map.addAttribute("sponsors", sponsorService.getTeamSponsorsList(team.getId()));

       List<Object[]> memb = teamService.getTeamTable(team.getId());

       for (int i=0; i<memb.size(); i++) {
           switch (memb.get(i)[2].toString()) {
               case "RACER": {
                   memb.get(i)[2] = "Гонщик";
                   break;
               }
               case "MECHANIC": {
                   memb.get(i)[2] = "Механик";
                   break;
               }
               case "CONSTRUCTOR": {
                   memb.get(i)[2] = "Конструктор";
                   break;
               }
               case "MANAGER": {
                   memb.get(i)[2] = "Менеджер";
                   break;
               }
           }
       }

       map.addAttribute("members", memb);

       map.addAttribute("achiv", constrCupResultService.getProfileAchievs(team.getId()));

        return "TeamProfilePage";

    }

}
