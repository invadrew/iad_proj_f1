package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.*;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RaceRegController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private RaceRegistrationService raceRegistrationService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CarService carService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private PilotingService pilotingService;

    @RequestMapping(value = "/race-reg", method = RequestMethod.GET)
    public String toRaceRegPage(ModelMap map, Authentication authentication) {

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

        List<Object[]> currRace = raceService.getCurrentEvent();
        map.addAttribute("currRace", currRace);

        Object[] r = currRace.get(0);

        List<Object[]> regTable = raceRegistrationService.getRegistrationTable(raceService.findTopByOrderByDateTimeDesc().getId());
        map.addAttribute("regTable", regTable);

        if (user.getSpec().equals(User.Spec.MANAGER)) {
            Team team = teamService.findById(teamMemberService.findByUserId(user.getId()).getTeam().getId());

            map.addAttribute("team", team);

            List<Object[]> teamMembers = teamMemberService.getAllRacers(team.getId());

            map.addAttribute("racers", teamMembers);

            List<Car> cars = carService.findAllByTeam(team);
            map.addAttribute("cars", cars);

            RaceRegistration registration = raceRegistrationService.findById(team, raceService.findById((int) r[6]));

            if (registration != null) {
                map.addAttribute("ifReg", true);
            } else {
                map.addAttribute("ifReg", false);
            }

        }

        Integer teamCount = 0;
            if (raceRegistrationService.canReg((int) r[6]) != null) { teamCount = raceRegistrationService.canReg((int) r[6]); }

        if (teamCount < raceService.findById((int) r[6]).getMaxParticipants()/2) {
            map.addAttribute("isOverflow", false);
        } else {
            map.addAttribute("isOverflow", true);
        }

        return "RaceRegistrationPage";
    }

    @RequestMapping(value = "/race-reg/registration", method = RequestMethod.POST)
    @ResponseBody
    public void registrate(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String firstP = request.getParameter("firstP");
        String secondP = request.getParameter("secondP");
        String firstC = request.getParameter("firstC");
        String secondC = request.getParameter("secondC");
        Integer raceId = Integer.parseInt(request.getParameter("raceId"));
        Integer teamId = Integer.parseInt(request.getParameter("team"));

        Integer firstPId = null;
        Integer firstCId = null;
        Integer secondPId = null;
        Integer secondCId = null;

        if (!firstP.equals("-")) { firstPId = Integer.parseInt(firstP); }
        if (!firstC.equals("-")) { firstCId = Integer.parseInt(firstC); }
        if (!secondP.equals("-")) { secondPId = Integer.parseInt(secondP); }
        if (!secondC.equals("-")) { secondCId = Integer.parseInt(secondC); }

        if (firstCId == null && firstPId == null && secondPId != null && secondCId != null ) {
            firstCId = secondCId; firstPId = secondPId; secondPId = null; secondCId = null;
        }

        Car firstCar = null;
        if (firstCId != null) { firstCar = carService.findById(firstCId); }
        Car secondCar = null;
        if (secondCId != null) { secondCar = carService.findById(secondCId); }
        TeamMember teamMemberF = null;
        if (firstPId != null) { teamMemberF = teamMemberService.findByUserId(firstPId); }
        TeamMember teamMemberS = null;
        if (secondPId != null) { teamMemberS = teamMemberService.findByUserId(secondPId); }

           try { Piloting firstPiloting = pilotingService.findByCarIdAndRacerId(firstCId, firstPId);
            if (firstPiloting == null) {
                Piloting newPiloting = new Piloting(firstCar, userService.findById(firstPId));
                pilotingService.save(newPiloting);
            }
         Piloting secondPiloting = pilotingService.findByCarIdAndRacerId(secondCId, secondPId);
            if ( secondPiloting == null ) {
                Piloting newPilotingSecond = new Piloting(secondCar, userService.findById(secondPId));
                pilotingService.save(newPilotingSecond);
            }
        } catch (NullPointerException x) { }

        RaceRegistration raceRegistration = new RaceRegistration(teamService.findById(teamId), raceService.findById(raceId),
                teamMemberF, firstCar, teamMemberS, secondCar, AcceptStatus.ACCEPTED);
        if (secondPId!= null && secondCId!= null) {
            raceRegistrationService.addNewRegRecord(teamId, raceId, firstPId, firstCId, secondPId, secondCId);
        } else {
            raceRegistrationService.addNewRegRecordOne(teamId,raceId,firstPId,firstCId);
        }
    }

}