package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class DetailAddController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarcaseStorageService carcaseStorageService;

    @Autowired
    private ChassisStorageService chassisStorageService;

    @Autowired
    private EngineStorageService engineStorageService;

    @Autowired
    private ElectronicsStorageService electronicsStorageService;

    @RequestMapping(value = "/add_detail", method = RequestMethod.GET)
    public String toGarage(ModelMap map, Authentication authentication) {

        User user = userService.findByLogin(authentication.getName());
        Team team = teamMemberService.findByUserId(user.getId()).getTeam();

        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);
        map.addAttribute("team", team);

        List<Car> cars = carService.findAllByTeam(team);

        List<ChassisStorage> chassis = new ArrayList<>();
        List<EngineStorage> engines = new ArrayList<>();
        List<CarcaseStorage> carcases = new ArrayList<>();
        List<ElectronicsStorage> electronics = new ArrayList<>();

        for (Car car: cars) {

            if (!car.getStatus().equals(AcceptStatus.REFUSED)) {
                engines.add(car.getCurrentEngine());
                carcases.add(car.getCurrentCarcase());
                chassis.add(car.getCurrentChassis());
                electronics.add(car.getCurrentElectronics());
            }
        }

        List<CarcaseStorage> teamCarc = carcaseStorageService.findAllByTeam(team);
        List<ElectronicsStorage> teamElec = electronicsStorageService.findAllByTeam(team);
        List<ChassisStorage> teamChass = chassisStorageService.findAllByTeam(team);
        List<EngineStorage> teamEng = engineStorageService.findAllByTeam(team);

        List<CarcaseStorage> freeCarc = new ArrayList<>();
        List<ElectronicsStorage> freeElec = new ArrayList<>();
        List<ChassisStorage> freeChass = new ArrayList<>();
        List<EngineStorage> freeEng = new ArrayList<>();

        for (CarcaseStorage carcase: teamCarc) {
            if (!carcases.contains(carcase)) {
                freeCarc.add(carcase);
            }
        }

        for (ElectronicsStorage electr: teamElec) {
            if (!electronics.contains(electr)) {
                freeElec.add(electr);
            }
        }

        for (ChassisStorage ch: teamChass) {
            if (!chassis.contains(ch)) {
                freeChass.add(ch);
            }
        }

        for (EngineStorage eng: teamEng) {
            if (!engines.contains(eng)) {
                freeEng.add(eng);
            }
        }

        map.addAttribute("freeCarc", freeCarc);
        map.addAttribute("freeChass", freeChass);
        map.addAttribute("freeEng", freeEng);
        map.addAttribute("freeElec", freeElec);


        return "DetailAddPage";
    }

    @RequestMapping(value = "/add_detail/addCar", method = RequestMethod.POST)
    @ResponseBody
    public void addCar(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        String label = request.getParameter("label");
        String model = request.getParameter("model");

        Integer carcase = Integer.parseInt(request.getParameter("carcase"));
        Integer chassis = Integer.parseInt(request.getParameter("chassis"));
        Integer engine = Integer.parseInt(request.getParameter("engine"));
        Integer electronics = Integer.parseInt(request.getParameter("electronics"));

        Car car = new Car(label,model, Calendar.getInstance().get(Calendar.YEAR),
                teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),
                null, Boolean.TRUE, carcaseStorageService.findById(carcase), engineStorageService.findById(engine), chassisStorageService.findById(chassis),
                electronicsStorageService.findById(electronics), AcceptStatus.ACCEPTED);

        car.setStatus(AcceptStatus.ACCEPTED);
        car.setCurrentElectronics(electronicsStorageService.findById(electronics));
        car.setCurrentCarcase(carcaseStorageService.findById(carcase));
        car.setCurrentChassis(chassisStorageService.findById(chassis));
        car.setCurrentEngine(engineStorageService.findById(engine));
        car.setModel(model);
        car.setLabel(label);
        car.setCreationYear(Calendar.getInstance().get(Calendar.YEAR));
        car.setTeam( teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()));
        car.setIsReady(Boolean.TRUE);

        carService.save(car);

    }

}