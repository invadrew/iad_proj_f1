package com.rogo.inv.iadprojf1.controller;


import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.SponsorService;
import com.rogo.inv.iadprojf1.service.TeamMemberService;
import com.rogo.inv.iadprojf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private TeamMemberService teamMemberService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String toAdminPanel() {
        return "AdminPage";
    }


    @RequestMapping(value = "/admin/regSponsor", method = RequestMethod.POST)
    @ResponseBody
    public String regSponsor(HttpServletResponse response, HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("passw");
        String name = request.getParameter("name");
        Double budget = Double.parseDouble(request.getParameter("budget"));

        User ifExists = userService.findByLogin(login);

        if (ifExists != null) { return "exists"; }

        Md4PasswordEncoder passwordEncoder = new Md4PasswordEncoder();
        User user = new User(login, passwordEncoder.encode(password), User.Spec.SPONSOR, null, AcceptStatus.ACCEPTED, null);
        userService.save(user);

        Sponsor sponsor = new Sponsor(user.getId(), user, name, budget, null);
        sponsorService.save(sponsor);

        return "ok";
    }

    @RequestMapping(value = "/admin/regAdmin", method = RequestMethod.POST)
    @ResponseBody
    public String regAdmin(HttpServletResponse response, HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("passw");

        User ifExists = userService.findByLogin(login);

        if (ifExists != null) { return "exists"; }

        Md4PasswordEncoder passwordEncoder = new Md4PasswordEncoder();
        User user = new User(login, passwordEncoder.encode(password), User.Spec.ADMIN, null, AcceptStatus.ACCEPTED, null);
        userService.save(user);

        return "ok";

    }

    @RequestMapping(value = "/admin/regTeamMember", method = RequestMethod.POST)
    @ResponseBody
    public String regTeamMember(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("passw");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String type = request.getParameter("type");

        User ifExists = userService.findByLogin(login);
        if (ifExists != null) { return "exists"; }

        User.Spec spec = User.Spec.RACER;
        switch (type) {
            case "Racer":
                spec = User.Spec.RACER;
                break;
            case "Mechanic":
                spec = User.Spec.MECHANIC;
                break;
            case "Constructor":
                spec = User.Spec.CONSTRUCTOR;
                break;
            case "Manager":
                spec = User.Spec.MANAGER;
                break;
        }

        Md4PasswordEncoder passwordEncoder = new Md4PasswordEncoder();
        User user = new User(login, passwordEncoder.encode(password), spec, null, AcceptStatus.ACCEPTED, null);
        userService.save(user);

        TeamMember teamMember = new TeamMember(user.getId(), user, name, surname, false, null);
        teamMemberService.save(teamMember);

        return "ok";

    }


}
