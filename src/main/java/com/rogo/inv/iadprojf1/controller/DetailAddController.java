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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class DetailAddController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/pictures";

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

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/add_detail", method = RequestMethod.GET)
    public String toGarage(ModelMap map, Authentication authentication) {

        map.addAttribute("myPhoto", userService.findByLogin(authentication.getName()).getPhoto().getPath());

        User user = userService.findByLogin(authentication.getName());
        Team team = teamMemberService.findByUserId(user.getId()).getTeam();

        String name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        map.addAttribute("name", name);
        map.addAttribute("team", team);
        map.addAttribute("budget", team.getBudget());

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
            if (!carcases.contains(carcase) && (carcase.getStatus().equals(AcceptStatus.ACCEPTED))) {
                freeCarc.add(carcase);
            }
        }

        for (ElectronicsStorage electr: teamElec) {
            if (!electronics.contains(electr) && (electr.getStatus().equals(AcceptStatus.ACCEPTED))) {
                freeElec.add(electr);
            }
        }

        for (ChassisStorage ch: teamChass) {
            if (!chassis.contains(ch) && (ch.getStatus().equals(AcceptStatus.ACCEPTED))) {
                freeChass.add(ch);
            }
        }

        for (EngineStorage eng: teamEng) {
            if (!engines.contains(eng) && (eng.getStatus().equals(AcceptStatus.ACCEPTED))) {
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

        User currConstrU = userService.findByLogin(authentication.getName());
        TeamMember currConstrTm = teamMemberService.findByUserId(currConstrU.getId());
        AcceptStatus status;

        if (currConstrTm.getCanBuy()) { status = AcceptStatus.ACCEPTED; } else { status = AcceptStatus.ON_REVIEW; }

        Car car = new Car(label,model, Calendar.getInstance().get(Calendar.YEAR),
                teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),
                photoService.findById(2), Boolean.TRUE, carcaseStorageService.findById(carcase), engineStorageService.findById(engine), chassisStorageService.findById(chassis),
                electronicsStorageService.findById(electronics), status, currConstrU);

        car.setStatus(status);
        car.setSender(currConstrU);
        car.setPhoto(photoService.findById(2));
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

    @RequestMapping(value = "/add_detail/addCarWithPhoto", method = RequestMethod.POST)
    @ResponseBody
    public void addCarWithPhoto(@RequestParam("file") MultipartFile multipartFile , @RequestParam("label") String label,
                                @RequestParam("model") String model, @RequestParam("carcase") Integer carcase,
                                @RequestParam("chassis") Integer chassis, @RequestParam("engine") Integer engine,
                                @RequestParam("electronics") Integer electronics, Authentication authentication) {


        User currConstrU = userService.findByLogin(authentication.getName());
        TeamMember currConstrTm = teamMemberService.findByUserId(currConstrU.getId());
        AcceptStatus status;

        if (currConstrTm.getCanBuy()) { status = AcceptStatus.ACCEPTED; } else { status = AcceptStatus.ON_REVIEW; }

        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(uploadDirectory, multipartFile.getOriginalFilename());
        fileNames.append(multipartFile.getOriginalFilename() + " ");

        try {
            Files.write(fileNameAndPath, multipartFile.getBytes());

            Photo photo = new Photo("/pictures/" + multipartFile.getOriginalFilename());
            photoService.save(photo);

            Car car = new Car(label, model, Calendar.getInstance().get(Calendar.YEAR),
                    teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),
                    photo, Boolean.TRUE, carcaseStorageService.findById(carcase), engineStorageService.findById(engine), chassisStorageService.findById(chassis),
                    electronicsStorageService.findById(electronics), status, currConstrU);

            car.setStatus(status);
            car.setSender(currConstrU);
            car.setPhoto(photo);
            car.setCurrentElectronics(electronicsStorageService.findById(electronics));
            car.setCurrentCarcase(carcaseStorageService.findById(carcase));
            car.setCurrentChassis(chassisStorageService.findById(chassis));
            car.setCurrentEngine(engineStorageService.findById(engine));
            car.setModel(model);
            car.setLabel(label);
            car.setCreationYear(Calendar.getInstance().get(Calendar.YEAR));
            car.setTeam(teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()));
            car.setIsReady(Boolean.TRUE);

            carService.save(car);

        } catch (IOException c) {
            c.printStackTrace();
        }
    }

    @RequestMapping(value = "/add_detail/addCarcase", method = RequestMethod.POST)
    @ResponseBody
    public void addCarcase(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        String material = request.getParameter("material");
        String rearWing = request.getParameter("rear_wing");
        String wings = request.getParameter("wings");
        String safeArcs = request.getParameter("safe_arcs");
        Double price = Double.parseDouble(request.getParameter("carcPrice"));

        User currConstrU = userService.findByLogin(authentication.getName());
        TeamMember currConstrTm = teamMemberService.findByUserId(currConstrU.getId());
        AcceptStatus status;

        if (currConstrTm.getCanBuy()) { status = AcceptStatus.ACCEPTED;
            Team team = currConstrTm.getTeam();
            team.setBudget(team.getBudget() - price);
            teamService.save(team);
        } else { status = AcceptStatus.ON_REVIEW; }

        CarcaseStorage carcaseStorage = new CarcaseStorage(teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),
                ComponentCondition.PERFECT, price, status, currConstrU, material, rearWing, safeArcs, wings);

        carcaseStorageService.save(carcaseStorage);

    }


    @RequestMapping(value = "/add_detail/addChassis", method = RequestMethod.POST)
    @ResponseBody
    public void addChassis(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        String model = request.getParameter("model");
        Float height = Float.parseFloat(request.getParameter("height"));
        Float width = Float.parseFloat(request.getParameter("width"));
        Double price = Double.parseDouble(request.getParameter("chassPrice"));

        User currConstrU = userService.findByLogin(authentication.getName());
        TeamMember currConstrTm = teamMemberService.findByUserId(currConstrU.getId());
        AcceptStatus status;

        if (currConstrTm.getCanBuy()) { status = AcceptStatus.ACCEPTED;
            Team team = currConstrTm.getTeam();
            team.setBudget(team.getBudget() - price);
            teamService.save(team);
        } else { status = AcceptStatus.ON_REVIEW; }

        ChassisStorage chassisStorage = new ChassisStorage(teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),
                ComponentCondition.PERFECT, price, status, currConstrU, model, height, width);


        chassisStorageService.save(chassisStorage);
    }

    @RequestMapping(value = "/add_detail/addEngine", method = RequestMethod.POST)
    @ResponseBody
    public void addEngine(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        String model = request.getParameter("model");
        Integer cyclinders = Integer.parseInt(request.getParameter("cyclinders"));
        Float stroke = Float.parseFloat(request.getParameter("stroke"));
        Float mass = Float.parseFloat(request.getParameter("mass"));
        Float capacity = Float.parseFloat(request.getParameter("capacity"));
        Double price = Double.parseDouble(request.getParameter("engPrice"));

        User currConstrU = userService.findByLogin(authentication.getName());
        TeamMember currConstrTm = teamMemberService.findByUserId(currConstrU.getId());
        AcceptStatus status;

        if (currConstrTm.getCanBuy()) { status = AcceptStatus.ACCEPTED;
            Team team = currConstrTm.getTeam();
            team.setBudget(team.getBudget() - price);
            teamService.save(team);
        } else { status = AcceptStatus.ON_REVIEW; }

        EngineStorage engineStorage = new EngineStorage(teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),
                ComponentCondition.PERFECT, price, status, currConstrU, model, cyclinders, capacity, mass, stroke);

        engineStorageService.save(engineStorage);

    }

    @RequestMapping(value = "/add_detail/addElectronics", method = RequestMethod.POST)
    @ResponseBody
    public void addElectronics(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {

        String telemetry = request.getParameter("telemetry");
        String controlSystem = request.getParameter("controlSystem");
        Double price = Double.parseDouble(request.getParameter("elecPrice"));

        User currConstrU = userService.findByLogin(authentication.getName());
        TeamMember currConstrTm = teamMemberService.findByUserId(currConstrU.getId());
        AcceptStatus status;

        if (currConstrTm.getCanBuy()) { status = AcceptStatus.ACCEPTED;
            Team team = currConstrTm.getTeam();
            team.setBudget(team.getBudget() - price);
            teamService.save(team);
        } else { status = AcceptStatus.ON_REVIEW; }

        ElectronicsStorage electronicsStorage = new ElectronicsStorage(teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),
                ComponentCondition.PERFECT, price, status, currConstrU, telemetry, controlSystem);

        electronicsStorageService.save(electronicsStorage);

    }

}