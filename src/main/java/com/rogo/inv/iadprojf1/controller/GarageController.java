package com.rogo.inv.iadprojf1.controller;

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

import java.util.ArrayList;
import java.util.List;

@Controller
public class GarageController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private CarService carService;

    @Autowired
    private ChassisStorageService chassisStorageService;

    @Autowired
    private EngineStorageService engineStorageService;

    @Autowired
    private ElectronicsStorageService electronicsStorageService;

    @Autowired
    private CarcaseStorageService carcaseStorageService;

    @RequestMapping(value = "/garage", method = RequestMethod.GET)
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
            chassis.add(car.getCurrentChassis());
            engines.add(car.getCurrentEngine());
            carcases.add(car.getCurrentCarcase());
            electronics.add(car.getCurrentElectronics());
        }
        
        map.addAttribute("cars", cars);

        map.addAttribute("chassis", chassis);
        map.addAttribute("engines",engines);
        map.addAttribute("carcases", carcases);
        map.addAttribute("electronics", electronics);

        return "GaragePage";
    }
}
