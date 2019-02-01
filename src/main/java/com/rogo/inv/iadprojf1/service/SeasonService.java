package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.cup.Championship;

import java.util.List;

public interface SeasonService {
    List<Season> findAll();

    Season save(Season entity);

    void delete(Season entity);

    Season findByYear(int id);

    Season findTopByOrderByYearDesc();
}