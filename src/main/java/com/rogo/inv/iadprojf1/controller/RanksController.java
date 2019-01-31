package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.service.ConstrCupResultService;
import com.rogo.inv.iadprojf1.service.SeasonService;
import com.rogo.inv.iadprojf1.service.WorldCupResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RanksController {

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private ConstrCupResultService constrCupResultService;

    @Autowired
    private WorldCupResultService worldCupResultService;

    @RequestMapping(value = "/ranks", method = RequestMethod.GET)
    public String toRanks(ModelMap map) {
        List<Season> seasonsList= seasonService.findAll();
        map.addAttribute("seasonsList",seasonsList);
        return "RanksPage";
    }

    @RequestMapping(value = "/ranks/world", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getWorldData(@RequestParam Integer year) {
        return worldCupResultService.getResTable(year);
    }

    @RequestMapping(value = "/ranks/constr", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getConstrData(@RequestParam Integer year) {
        return constrCupResultService.getConstrCupResultTable(year);
    }

}
