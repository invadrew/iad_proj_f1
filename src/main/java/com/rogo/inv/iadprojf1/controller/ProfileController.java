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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private RaceResultService raceResultService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private ConstrCupResultService constrCupResultService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String toProfile(ModelMap map, Authentication authentication, @Param("id") Integer id) {

        User user;

        if (id != null) {
             user = userService.findById(id);
        } else {
            user = userService.findByLogin(authentication.getName());
        }

        map.addAttribute("user",user);

        String name = "Панель администратора";

        if (userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.SPONSOR)) {
           name = sponsorService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName();
        }

        if (!(userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.SPONSOR)) && !(userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.ADMIN))) {
            name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                    teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        }
        map.addAttribute("name", name);

        if (user.getSpec().equals(User.Spec.RACER)) {

            //Racer stats

            Object pC = teamMemberService.pointsCount(user.getId(), seasonService.findTopByOrderByYearDesc().getYear());
            if (pC == null) {
                pC = "в этом сезоне ещё нет очков";
            }
            map.addAttribute("pointsCount", pC);

            Object rC = teamMemberService.racingsCount(user.getId(), seasonService.findTopByOrderByYearDesc().getYear());
            if (rC == null) {
                rC = "в этом сезоне ещё нет гонок";
            }
            map.addAttribute("racingsCount", rC);

            Object aRC = teamMemberService.allRaceCount(user.getId());
            if (aRC == null) {
                aRC = "пока ни одной гонки";
            }
            map.addAttribute("allRaceCount", aRC);

            Object cW = teamMemberService.cupsWon(user.getId());
            if (cW == null) {
                cW = "ни одного кубка";
            }
            map.addAttribute("cupsWon", cW);

            Object cC = teamMemberService.champCount(user.getId());
            if (cC == null) {
                cC = "ни одного чемпионата";
            }
            map.addAttribute("champCount", cC);

            Object aPAA = teamMemberService.avergePlaceAtAll(user.getId());
            if (aPAA == null) {
                aPAA = "пока ни одной гонки";
            }
            map.addAttribute("averagePlaceAtAll", aPAA);

            Object aPAS = teamMemberService.avergePlaceAtSeason(user.getId(), seasonService.findTopByOrderByYearDesc().getYear());
            if (aPAS == null) {
                aPAS = "ещё ни одной гонки за сезон";
            }
            map.addAttribute("averagePlaceAtSeason", aPAS);

            Object bP = teamMemberService.bestPlace(user.getId(), seasonService.findTopByOrderByYearDesc().getYear());
            if (bP == null) {
                bP = "пока ни одной гонки";
            }
            map.addAttribute("bestPlace", bP);

            Object gBTT = teamMemberService.getBestTrackTime(user.getId());
            if (gBTT == null) {
                gBTT = "пока ни одной гонки";
            }
            map.addAttribute("getBestTrackTime", gBTT);

            // Champs stats

            List<Object[]> gRPT = raceResultService.getRacerProfileTable(user.getId());
            map.addAttribute("getRacerProfileTable", gRPT);
        }

            // Name and Surname

            String nameSurname = teamMemberService.findByUserId(user.getId()).getName() + " " +
                    teamMemberService.findByUserId(user.getId()).getSurname();
            map.addAttribute("nameSurname",nameSurname);

            // Team name

            Team team = teamMemberService.findByUserId(user.getId()).getTeam();
            try { map.addAttribute("team",team.getName()); } catch (NullPointerException n) {
                map.addAttribute("team","Нет команды");
            }

            // Spec

            switch (user.getSpec()) {
                case RACER: {
                    map.addAttribute("spec","Гонщик");
                    break;
                }
                case MECHANIC: {
                    map.addAttribute("spec","Механик");
                    break;
                }
                case CONSTRUCTOR: {
                    map.addAttribute("spec","Конструктор");
                    break;
                }
                case MANAGER: {
                    map.addAttribute("spec","Менеджер");
                    break;
                }
            }

           try { List<Object[]> teamAch = constrCupResultService.getProfileAchievs(team.getId());
            map.addAttribute("ach",teamAch); } catch (NullPointerException n) {map.addAttribute("ach",null);}

            map.addAttribute("currName",authentication.getName());

     try {
         TeamMember teamMember = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId());
         Team teamCurr = teamMember.getTeam();
         map.addAttribute("currUserSpec",userService.findByLogin(authentication.getName()).getSpec());
         map.addAttribute("currUserTeam", teamCurr.getId());
         map.addAttribute("ifCanBuy", teamMember.getCanBuy());

    } catch (NullPointerException x) {
         map.addAttribute("currUserSpec", null);
         map.addAttribute("currUserTeam", null);
         map.addAttribute("ifCanBuy", null);
     }

        map.addAttribute("currUserId", userService.findByLogin(authentication.getName()).getId());

        return "UserProfilePage";
    }

    @RequestMapping(value = "/profile/addTeam", method = RequestMethod.POST)
    @ResponseBody
    public String addTeam(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String id = request.getParameter("id");

        if (teamService.findByName(name) != null) { return "busy"; }

        Team team = new Team(name, 0, AcceptStatus.ON_REVIEW, null, userService.findById(Integer.parseInt(id)));
        teamService.save(team);

        return "ok";
    }

}