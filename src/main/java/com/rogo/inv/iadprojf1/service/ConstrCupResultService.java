package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.cup.ConstrCupResult;

import java.util.List;

public interface ConstrCupResultService {
    List<ConstrCupResult> findAll();

    ConstrCupResult save(ConstrCupResult entity);

    void delete(ConstrCupResult entity);

    ConstrCupResult findById(int team, int season);

    List<Object[]> getConstrCupResultTable(int season);

    List<Object[]> getRates();

    List<Object[]> getProfileAchievs(int team);

    int getPlaceSeason(int season, int team);
}