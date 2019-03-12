package com.rogo.inv.iadprojf1.controller;


import com.rogo.inv.iadprojf1.entity.*;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import com.rogo.inv.iadprojf1.service.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/pictures";

    @Autowired
    private UserService userService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private ChampionshipService championshipService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private RaceRegistrationService raceRegistrationService;

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String toAdminPanel(ModelMap map) {

        Season currSeas = seasonService.findTopByOrderByYearDesc();
        map.addAttribute("currSeason", currSeas);

        List<Team> onReviewTeams = teamService.getAllByStatus(AcceptStatus.ON_REVIEW);
        List<Object[]> revTeams = new ArrayList<>();

        for (Team team:onReviewTeams) {
            Object[] tezmObj = {team.getId(), team.getName(), team.getBudget(), team.getStatus(), team.getComments(), userService.findById(team.getSender().getId()).getId()};
            revTeams.add(tezmObj);
        }

        map.addAttribute("teamsOnReview",revTeams);

        Race currentRace = raceService.findTopByOrderByDateTimeDesc();
        List<RaceRegistration> allRegs = raceRegistrationService.findAllByRace(currentRace);
        List<Object[]> regsOnReview = new ArrayList<>();

        for (RaceRegistration registration:allRegs) {

            if (registration.getStatus().equals(AcceptStatus.ON_REVIEW)) {

                String secondName; Integer secondId;
                try {
                    secondName = registration.getSecondPilot().getName() + " " + registration.getSecondPilot().getSurname();
                    secondId = registration.getSecondPilot().getUserId();
                } catch (NullPointerException x) {
                    secondId = null;
                    secondName = null;
                }

                Object[] regInfo = { registration.getRace().getId(), registration.getTeam().getId(), registration.getRace().getDateTime(), registration.getRace().getChamp().getName(),
                registration.getRace().getChamp().getSeason().getYear(), registration.getTeam().getName(),
                        registration.getFirstPilot().getName() + " " + registration.getFirstPilot().getSurname(), secondName ,
                registration.getFirstPilot().getUserId(), secondId};
                regsOnReview.add(regInfo);
            }

        }

        map.addAttribute("regsOnReview", regsOnReview);

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
        User user = new User(login, passwordEncoder.encode(password), User.Spec.SPONSOR, photoService.findById(1), AcceptStatus.ACCEPTED, null);
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
        User user = new User(login, passwordEncoder.encode(password), User.Spec.ADMIN, photoService.findById(1), AcceptStatus.ACCEPTED, null);
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
        User user = new User(login, passwordEncoder.encode(password), spec, photoService.findById(1), AcceptStatus.ACCEPTED, null);
        userService.save(user);

        if (spec.equals(User.Spec.MANAGER)) {

            TeamMember teamMember = new TeamMember(user.getId(), user, name, surname, true, null);
            user.setBuyStatus(AcceptStatus.ACCEPTED);
            userService.save(user);
            teamMemberService.save(teamMember);

        } else {

            TeamMember teamMember = new TeamMember(user.getId(), user, name, surname, false, null);
            teamMemberService.save(teamMember);
        }
        return "ok";

    }

    @RequestMapping(value = "/admin/addRace", method = RequestMethod.POST)
    @ResponseBody
    public void addRace(HttpServletResponse response, HttpServletRequest request) throws ParseException {

        String dateTime = request.getParameter("date");
        String champ = request.getParameter("champ");
        String track = request.getParameter("track");
        Integer laps = Integer.parseInt(request.getParameter("raceNum"));
        Integer teamNum = Integer.parseInt(request.getParameter("teamNum")) * 2;
        String country = request.getParameter("country");

        LocalDateTime raceDate = LocalDateTime.parse(dateTime);
        Date rDate = Date.from(raceDate.atZone(ZoneId.systemDefault()).toInstant());
       // Date raceDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTime);
        Season year;

        Season ifExistsYear = seasonService.findByYear(raceDate.getYear());
        if (ifExistsYear == null) {
            year = new Season(raceDate.getYear());
            seasonService.save(year);
        } else {
            year = ifExistsYear;
        }

        Integer stage;
        try {
         stage = raceService.getStageNum(year.getYear()) + 1; } catch (NullPointerException x) {
            stage = 1;
        }

        Championship championship = new Championship(year, champ, country, stage);
        championshipService.save(championship);
        Race race = new Race(championship, null, rDate, laps, teamNum, track);
        raceService.save(race);

    }

    @RequestMapping(value = "/admin/handleTeamRequest", method = RequestMethod.POST)
    @ResponseBody
    public void handleTeam(HttpServletResponse response, HttpServletRequest request) {

        String comment = request.getParameter("comment");
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Integer teamId = Integer.parseInt(request.getParameter("teamid"));
        Integer senderId = Integer.parseInt(request.getParameter("senderid"));

        if (status) {

            Team team = teamService.findById(teamId);
            team.setStatus(AcceptStatus.ACCEPTED);
            team.setComments(comment);
            teamService.save(team);

            TeamMember teamMember = teamMemberService.findByUserId(senderId);
            teamMember.setTeam(team);
            teamMember.setCanBuy(Boolean.TRUE);
            teamMemberService.save(teamMember);

        } else {

            Team team = teamService.findById(teamId);
            team.setStatus(AcceptStatus.REFUSED);
            team.setComments(comment);
            teamService.save(team);

        }

    }

    @RequestMapping(value = "/admin/handleRaceRequest", method = RequestMethod.POST)
    @ResponseBody
    public void handleRace(HttpServletRequest request, HttpServletResponse response) {

        String comment = request.getParameter("comment");
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Integer teamId = Integer.parseInt(request.getParameter("team"));
        Integer raceId = Integer.parseInt(request.getParameter("race"));

        if (status) {

            RaceRegistration registration = raceRegistrationService.findById(teamService.findById(teamId),raceService.findById(raceId));
            registration.setStatus(AcceptStatus.ACCEPTED);
            registration.setComment(comment);
            raceRegistrationService.updRegRequest(AcceptStatus.ACCEPTED,comment,raceService.findById(raceId), teamService.findById(teamId));

        } else {

            RaceRegistration registration = raceRegistrationService.findById(teamService.findById(teamId),raceService.findById(raceId));
            registration.setStatus(AcceptStatus.REFUSED);
            registration.setComment(comment);
            raceRegistrationService.updRegRequest(AcceptStatus.REFUSED,comment,raceService.findById(raceId), teamService.findById(teamId));

        }

    }

    @RequestMapping(value = "/admin/addPhoto", method = RequestMethod.POST)
    @ResponseBody
    public void setPhoto(@RequestParam("file") MultipartFile multipartFile, @RequestParam("login") String login) {

        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(uploadDirectory, multipartFile.getOriginalFilename());
        fileNames.append(multipartFile.getOriginalFilename() + " ");
        try {
            Files.write(fileNameAndPath, multipartFile.getBytes());

            User user = userService.findByLogin(login);
            Photo photo = new Photo("/pictures/" + multipartFile.getOriginalFilename());
            photoService.save(photo);
            user.setPhoto(photo);
            userService.save(user);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
