package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
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

        Team team;

        if (id == null) {
            if (user.getStatus().equals(AcceptStatus.ACCEPTED)) { team = teamMemberService.findByUserId(user.getId()).getTeam(); } else {
                team = null;
            }
        } else {
            team = teamService.findById(id);
        }

        if (team != null) {

            map.addAttribute("team", team);
          try {  map.addAttribute("currUserTeam",teamMemberService.findByUserId(user.getId()).getTeam());

              map.addAttribute("currUSpec",user.getSpec().toString());

              List<TeamMember> allMemb = teamMemberService.getAllspecificType(teamMemberService.findByUserId(user.getId()).getTeam().getId(), Spec.MANAGER.toString());
              if (allMemb.size() <= 1) { map.addAttribute("canLeave", false); } else {  map.addAttribute("canLeave", true); }

          }
          catch (NullPointerException c) { map.addAttribute("currUserTeam",null); }

            Object gSP = teamService.getSeasPoints(seasonService.findTopByOrderByYearDesc().getYear(), team.getId());
            if (gSP == null) {
                gSP = "ещё нет данных за сезон";
            }
            map.addAttribute("seasonPoints", gSP);

            Object gBP = teamService.getBestPlace(team.getId());
            if (gBP == null) {
                gBP = "ещё нет кубков";
            }
            map.addAttribute("bestPlace", gBP);

            Object racersCount = teamService.getRacersCount(team.getId());
            if (racersCount == null) {
                racersCount = "ни одного гонщика";
            }
            map.addAttribute("racers", racersCount);

            Object mechanicCount = teamService.getMechanicsCount(team.getId());
            if (mechanicCount == null) {
                mechanicCount = "ни одного механика";
            }
            map.addAttribute("mechanics", mechanicCount);

            Object constrCount = teamService.getConstrsCount(team.getId());
            if (constrCount == null) {
                constrCount = "ни одного конструктора";
            }
            map.addAttribute("constrs", constrCount);

            Object manCount = teamService.getManagersCount(team.getId());
            if (manCount == null) {
                manCount = "ни одного менеджера";
            }
            map.addAttribute("managers", manCount);

            map.addAttribute("bestRacer", teamService.bestRacer(team.getId()));

            map.addAttribute("sponsors", sponsorService.getTeamSponsorsList(team.getId()));

            List<Object[]> memb = teamService.getTeamTable(team.getId());

            for (int i = 0; i < memb.size(); i++) {
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
        }
        return "TeamProfilePage";

    }

    @RequestMapping(value = "/team/leave", method = RequestMethod.GET)
    @ResponseBody
    public void leaveTeam(Authentication authentication) {
        User user = userService.findByLogin(authentication.getName());
        TeamMember teamMember = teamMemberService.findByUserId(user.getId());
        teamMember.setTeam(null);
        teamMemberService.save(teamMember);
    }

    @RequestMapping(value = "/team/join", method = RequestMethod.POST)
    @ResponseBody
    public void joinTeam(HttpServletRequest request, Authentication authentication) {

        Integer teamId = Integer.parseInt(request.getParameter("teamId"));

        User candidateU = userService.findByLogin(authentication.getName());
        TeamMember candidateTm = teamMemberService.findByUserId(candidateU.getId());

        candidateU.setStatus(AcceptStatus.ON_REVIEW);
        candidateTm.setTeam(teamService.findById(teamId));

        userService.save(candidateU);
        teamMemberService.save(candidateTm);

    }
}
