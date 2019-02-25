package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.rogo.inv.iadprojf1.entity.ComponentCondition.ANY;

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

        map.addAttribute("carcaseStorage", carcaseStorageService.findAllByTeam(team));
        map.addAttribute("enginesStorage", engineStorageService.findAllByTeam(team));
        map.addAttribute("chassisStorage", chassisStorageService.findAllByTeam(team));
        map.addAttribute("electronicsStorage", electronicsStorageService.findAllByTeam(team));

        return "GaragePage";
    }

    @RequestMapping(value = "/garage/carcase", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getFilteredCarcases(@RequestParam String material, @RequestParam String rearWing,
                                                    @RequestParam String safetyArcs, @RequestParam String wings,
                                                    @RequestParam ComponentCondition condition, Authentication authentication) {
        List<CarcaseStorage> teamCarcases = carcaseStorageService.findAllByTeam(
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam());
        List<Object[]> filteredCarcases = new ArrayList<>();

        for (CarcaseStorage carc: teamCarcases) {
            if ((carc.getMaterial().equals(material) || material.equals("any")) &&
                    (carc.getRearWing().equals(rearWing) || (rearWing.equals("any"))) &&
                    (carc.getSafetyArcs().equals(safetyArcs) || (safetyArcs.equals("any")))
            && (carc.getWings().equals(wings) || (wings.equals("any")))
                    && (carc.getCondition().equals(condition) || (condition.equals(ANY)))) {

                String cond = getNamedCondition(carc.getCondition());

                Object[] characteristics = { carc.getMaterial(), carc.getRearWing(), carc.getSafetyArcs(), carc.getWings(), cond};
                filteredCarcases.add(characteristics);

            }
        }

        return filteredCarcases;
    }

    @RequestMapping(value = "/garage/electronics", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getFilteredElectronics(@RequestParam String telemetry, @RequestParam String controlSystem,
                                              @RequestParam ComponentCondition condition, Authentication authentication) {
        List<ElectronicsStorage> teamElectronics = electronicsStorageService.findAllByTeam(
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam());
        List<Object[]> filteredElectronics = new ArrayList<>();

        for (ElectronicsStorage elec: teamElectronics) {
            if ((elec.getTelemetry().equals(telemetry) || telemetry.equals("any")) &&
                    (elec.getControlSystem().equals(controlSystem) || controlSystem.equals("any"))
                    && (elec.getCondition().equals(condition) || (condition.equals(ANY)))) {

                String elCond = getNamedCondition(elec.getCondition());

                Object[] characteristics = { elec.getTelemetry(), elec.getControlSystem(), elCond};
                filteredElectronics.add(characteristics);

            }
        }

        return filteredElectronics;
    }

    @RequestMapping(value = "/garage/chassis", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getFilteredChassis(@RequestParam String model,
                                             @RequestParam Double heightFrom, @RequestParam Double heightTo,
                                             @RequestParam Double widthFrom, @RequestParam Double widthTo,
                                             @RequestParam ComponentCondition condition, Authentication authentication) {

        List<ChassisStorage> teamChassis = chassisStorageService.findAllByTeam(
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam());
        List<Object[]> filteredChassis = new ArrayList<>();


        for (ChassisStorage ch: teamChassis) {

            if ((ch.getModel().equals(model) || model.equals("any")) &&
                    ( (ch.getWidth() >= widthFrom) && (ch.getWidth() <= widthTo) )
                    && ((ch.getHeight() >= heightFrom) && (ch.getHeight() <= heightTo))
                    && (ch.getCondition().equals(condition) || (condition.equals(ANY)))) {

                String cond = getNamedCondition(ch.getCondition());

                Object[] characteristics = { ch.getModel(), ch.getHeight(), ch.getWidth(), cond};
                filteredChassis.add(characteristics);

            }
        }

        return filteredChassis;

    }


    @RequestMapping(value = "/garage/engines", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getFilteredEngines(@RequestParam String model,
                                             @RequestParam Double cyclFrom, @RequestParam Double cyclTo,
                                             @RequestParam Double capFrom, @RequestParam Double capTo,
                                             @RequestParam Double massFrom, @RequestParam Double massTo,
                                             @RequestParam Double strokeFrom, @RequestParam Double strokeTo,
                                             @RequestParam ComponentCondition condition, Authentication authentication) {

        List<EngineStorage> teamEngines = engineStorageService.findAllByTeam(
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam());
        List<Object[]> filteredEngines = new ArrayList<>();


        for (EngineStorage eng:teamEngines) {

            if ((eng.getModel().equals(model) || model.equals("any")) &&
                    ( (eng.getCyclinders() >= cyclFrom) && (eng.getCyclinders() <= cyclTo) )
                    && ((eng.getCapacity() >= capFrom) && (eng.getCapacity() <= capTo))
                    && ((eng.getMass() >= massFrom) && (eng.getMass() <= massTo))
                    && ((eng.getStroke() >= strokeFrom) && (eng.getStroke() <= strokeTo))
                    && (eng.getCondition().equals(condition) || (condition.equals(ANY)))) {

                String cond = getNamedCondition(eng.getCondition());

                Object[] characteristics = { eng.getModel(), eng.getCyclinders(), eng.getCapacity(), eng.getMass(), eng.getStroke(), cond};
                filteredEngines.add(characteristics);

            }
        }

        return filteredEngines;

    }

    private String getNamedCondition( @NotNull ComponentCondition condition) {
        String cond = "";
        switch (condition) {
            case AWFUL:
                cond = "Ужасное";
                break;
            case BAD:
                cond = "Плохое";
                break;
            case NORMAL:
                cond = "Нормальное";
                break;
            case GOOD:
                cond = "Хорошее";
                break;
            case PERFECT:
                cond = "Идеальное";
                break;
        }
        return cond;
    }

}
