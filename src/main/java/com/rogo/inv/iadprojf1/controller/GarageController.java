package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.rogo.inv.iadprojf1.entity.ComponentCondition.ANY;
import static com.rogo.inv.iadprojf1.entity.ComponentCondition.PERFECT;

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

    @Autowired
    private ComponentChangeService componentChangeService;

    @Autowired
    private TeamService teamService;

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
        List<Car> carList = new ArrayList<>();

        for (Car car: cars) {

            if (car.getStatus().equals(AcceptStatus.ACCEPTED)) {
                carList.add(car);

                chassis.add(car.getCurrentChassis());
                engines.add(car.getCurrentEngine());
                carcases.add(car.getCurrentCarcase());
                electronics.add(car.getCurrentElectronics());
            }
        }
        
        map.addAttribute("cars", carList);

        map.addAttribute("chassis", chassis);
        map.addAttribute("engines",engines);
        map.addAttribute("carcases", carcases);
        map.addAttribute("electronics", electronics);

        map.addAttribute("carcaseStorage", carcaseStorageService.findAllByTeam(team));
        map.addAttribute("enginesStorage", engineStorageService.findAllByTeam(team));
        map.addAttribute("chassisStorage", chassisStorageService.findAllByTeam(team));
        map.addAttribute("electronicsStorage", electronicsStorageService.findAllByTeam(team));

        map.addAttribute("canBuy", teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getCanBuy());

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

    @RequestMapping(value = "/garage/carcaseInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getCarcaseInfo(@RequestParam Integer carId) {

        CarcaseStorage carcaseStorage = carcaseStorageService.findById(carService.findById(carId).getCurrentCarcase().getId());

        return  carcaseStorage.getRearWing() + " "  +  carcaseStorage.getSafetyArcs() +
                " " + carcaseStorage.getWings();

    }

    @RequestMapping(value = "/garage/chassisInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object[] getChassisInfo(@RequestParam Integer carId) {

        ChassisStorage chassisStorage = chassisStorageService.findById(carService.findById(carId).getCurrentChassis().getId());

        Object[] info = { chassisStorage.getModel(), chassisStorage.getHeight(), chassisStorage.getWidth() };

        return info;

    }

    @RequestMapping(value = "/garage/engineInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object[] getEngineInfo(@RequestParam Integer carId) {

        EngineStorage engineStorage = engineStorageService.findById(carService.findById(carId).getCurrentEngine().getId());

        Object[] info = { engineStorage.getModel(), engineStorage.getStroke(), engineStorage.getMass(), engineStorage.getCapacity(), engineStorage.getCyclinders() };

        return info;

    }

    @RequestMapping(value = "/garage/electronicsInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object[] getElectronicsInfo(@RequestParam Integer carId) {

        ElectronicsStorage electronicsStorage = electronicsStorageService.findById(carService.findById(carId).getCurrentElectronics().getId());

        Object[] info = { electronicsStorage.getTelemetry(), electronicsStorage.getControlSystem() };

        return info;

    }

    @RequestMapping(value = "/garage/changeCarc", method = RequestMethod.POST)
    @ResponseBody
    public void changeCarc(HttpServletResponse response, HttpServletRequest request) {

        Integer car = Integer.parseInt(request.getParameter("carId"));
        Integer carcase = Integer.parseInt(request.getParameter("carcaseId"));

        ComponentChange componentChange = new ComponentChange(carService.findById(car), null, carcaseStorageService.findById(carService.findById(car).getCurrentCarcase().getId()),
                carcaseStorageService.findById(carcase), null, null, null, null,
                null, null, null, new Date());

        componentChangeService.save(componentChange);

        Car carr = carService.findById(car);

        carService.updCarcase(carcaseStorageService.findById(carcase),car);
        carr.setCurrentCarcase(carcaseStorageService.findById(carcase));
       // carService.save(carr);

    }

    @RequestMapping(value = "/garage/changeChass", method = RequestMethod.POST)
    @ResponseBody
    public void changeChass(HttpServletResponse response, HttpServletRequest request) {

        Integer car = Integer.parseInt(request.getParameter("carId"));
        Integer chassis = Integer.parseInt(request.getParameter("chassisId"));

        ComponentChange componentChange = new ComponentChange(carService.findById(car), null, null, null,
                chassisStorageService.findById(carService.findById(car).getCurrentChassis().getId()), chassisStorageService.findById(chassis), null, null,
                null, null, null, new Date());

        componentChangeService.save(componentChange);

        Car carr = carService.findById(car);

        carService.updChassis(chassisStorageService.findById(chassis),car);
        carr.setCurrentChassis(chassisStorageService.findById(chassis));
        // carService.save(carr);

    }

    @RequestMapping(value = "/garage/changeEng", method = RequestMethod.POST)
    @ResponseBody
    public void changeEng(HttpServletResponse response, HttpServletRequest request) {

        Integer car = Integer.parseInt(request.getParameter("carId"));
        Integer engine = Integer.parseInt(request.getParameter("engineId"));

        ComponentChange componentChange = new ComponentChange(carService.findById(car), null, null, null, null, null,
                engineStorageService.findById(carService.findById(car).getCurrentEngine().getId()), engineStorageService.findById(engine),
                null, null, null, new Date());

        componentChangeService.save(componentChange);

        Car carr = carService.findById(car);

        carService.updEngine(engineStorageService.findById(engine),car);
        carr.setCurrentEngine(engineStorageService.findById(engine));
        // carService.save(carr);

    }


    @RequestMapping(value = "/garage/changeElectronics", method = RequestMethod.POST)
    @ResponseBody
    public void changeElec(HttpServletResponse response, HttpServletRequest request) {

        Integer car = Integer.parseInt(request.getParameter("carId"));
        Integer electronics = Integer.parseInt(request.getParameter("electronicsId"));

        ComponentChange componentChange = new ComponentChange(carService.findById(car), null, null, null , null, null,
                null, null,
                electronicsStorageService.findById(carService.findById(car).getCurrentElectronics().getId()),
                electronicsStorageService.findById(electronics), null, new Date());

        componentChangeService.save(componentChange);

        Car carr = carService.findById(car);

        carService.updElectronics(electronicsStorageService.findById(electronics),car);
        carr.setCurrentElectronics(electronicsStorageService.findById(electronics));
        // carService.save(carr);

    }

    @RequestMapping(value = "/garage/disassemble", method = RequestMethod.POST)
    @ResponseBody
    public void carDisassemble(HttpServletRequest request, HttpServletResponse response) {

        Integer car = Integer.parseInt(request.getParameter("carId"));

        carService.refuseCar(car);
        carService.findById(car).setStatus(AcceptStatus.REFUSED);


    }

    @RequestMapping(value = "/garage/repairCarcase", method = RequestMethod.POST)
    @ResponseBody
    public String repairCarase(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        Integer car = Integer.parseInt(request.getParameter("carId"));
        CarcaseStorage carcase = carcaseStorageService.findById(carService.findById(car).getCurrentCarcase().getId());

        double price = conditionToRepairPrice(carcase.getCondition(), carcase.getPrice());

        if (price > team.getBudget()) return "error";

        teamService.updTeamBudget(team.getBudget() - price, team.getId());
        team.setBudget(team.getBudget() - price);

        carcaseStorageService.repairCarcase(carcase.getId());
        carcase.setCondition(PERFECT);

        return "ok";

    }

    @RequestMapping(value = "/garage/repairChassis", method = RequestMethod.POST)
    @ResponseBody
    public String repairChassis(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        Integer car = Integer.parseInt(request.getParameter("carId"));
        ChassisStorage chassis = chassisStorageService.findById(carService.findById(car).getCurrentChassis().getId());

        double price = conditionToRepairPrice(chassis.getCondition(), chassis.getPrice());

        if (price > team.getBudget()) return "error";

        teamService.updTeamBudget(team.getBudget() - price, team.getId());
        team.setBudget(team.getBudget() - price);

        chassisStorageService.repairChassis(chassis.getId());
        chassis.setCondition(PERFECT);

        return "ok";

    }

    @RequestMapping(value = "/garage/repairEngine", method = RequestMethod.POST)
    @ResponseBody
    public String repairEngine(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        Integer car = Integer.parseInt(request.getParameter("carId"));
        EngineStorage engine = engineStorageService.findById(carService.findById(car).getCurrentEngine().getId());

        double price = conditionToRepairPrice(engine.getCondition(), engine.getPrice());

        if (price > team.getBudget()) return "error";

        teamService.updTeamBudget(team.getBudget() - price, team.getId());
        team.setBudget(team.getBudget() - price);

        engineStorageService.repairEngine(engine.getId());
        engine.setCondition(PERFECT);

        return "ok";
    }

    @RequestMapping(value = "/garage/repairElectronics", method = RequestMethod.POST)
    @ResponseBody
    public String repairElectronics(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        Team team = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam();
        Integer car = Integer.parseInt(request.getParameter("carId"));
        ElectronicsStorage electronics = electronicsStorageService.findById(carService.findById(car).getCurrentElectronics().getId());

        double price = conditionToRepairPrice(electronics.getCondition(), electronics.getPrice());

        if (price > team.getBudget()) return "error";

        teamService.updTeamBudget(team.getBudget() - price, team.getId());
        team.setBudget(team.getBudget() - price);

        electronicsStorageService.repairElectronics(electronics.getId());
        electronics.setCondition(PERFECT);

        return "ok";
    }

    private double conditionToRepairPrice(@NotNull ComponentCondition condition, double price) {
        switch (condition) {
            case AWFUL:
                return Math.round(price/7);
            case BAD:
                return Math.round(price/8);
            case NORMAL:
                return Math.round(price/9);
            case GOOD:
                return Math.round(price/10);
        }

        return 0;
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
