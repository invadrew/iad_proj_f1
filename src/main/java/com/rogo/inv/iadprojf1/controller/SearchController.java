package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;
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

import java.util.Iterator;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String toSearch(ModelMap map, Authentication authentication, @Param("toSearch") String toSearch) {

        User user = userService.findByLogin(authentication.getName());

        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);

        List<TeamMember> users = teamMemberService.findAll();
        List<Team> teams = teamService.findAll();

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

        map.addAttribute("users", users);
        map.addAttribute("teams", teams);

        return "SearchPage";

    }

}