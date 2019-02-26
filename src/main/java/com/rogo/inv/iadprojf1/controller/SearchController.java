package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.SponsorService;
import com.rogo.inv.iadprojf1.service.TeamMemberService;
import com.rogo.inv.iadprojf1.service.TeamService;
import com.rogo.inv.iadprojf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String toSearch(ModelMap map, Authentication authentication, @RequestParam String toSearch, HttpServletRequest request, HttpServletResponse response) {

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

        List<TeamMember> users = teamMemberService.findAll();
        List<Team> teams = teamService.findAll();
        List<Sponsor> sponsors = sponsorService.findAll();

        Iterator<TeamMember> itU = users.iterator();
        while (itU.hasNext()) {
            TeamMember user1= itU.next();
            if (!(user1.getName().toLowerCase().contains(toSearch.toLowerCase()) ||
                    user1.getSurname().toLowerCase().contains(toSearch.toLowerCase()))) {
                itU.remove();
            }
            if (userService.findById(user1.getUserId()).getSpec().equals(User.Spec.ADMIN)) {
                itU.remove();
            }
        }

        Iterator<Team> itT = teams.iterator();
        while (itT.hasNext()) {
            Team team = itT.next();
            if (!(teamService.findById(team.getId()).getName().toLowerCase().contains(toSearch.toLowerCase()))) {
                itT.remove();
            }
        }

        Iterator<Sponsor> itS = sponsors.iterator();
        while (itS.hasNext()) {
            Sponsor sponsor = itS.next();
            if (!(sponsorService.findByUserId(userService.findById(sponsor.getUserId()).getId()).getName().toLowerCase()
                    .contains(toSearch.toLowerCase()))) {
                itS.remove();
            }
        }


       /* List<Object[]> user_names = new ArrayList<>();
        List<Object[]> team_names = new ArrayList<>();

        for ( int i=0; i<users.size(); i++) {
            String un = users.get(i).getName() + " " + users.get(i).getSurname();
            Integer id = users.get(i).getUserId();
            Object[] uO = {un , id };
            user_names.add(uO);
        }

        for ( int i=0; i<teams.size(); i++) {
            String tn = teams.get(i).getName();
            Integer id = teams.get(i).getId();
            Object[] tO = {tn, id };
            team_names.add(tO);
        }
*/
        map.addAttribute("users", users);
        map.addAttribute("teams", teams);
        map.addAttribute("sponsors", sponsors);

        return "SearchPage";

    }

}