package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.cup.ConstrCupResult;

import java.util.List;

public interface ConstrCupResultService {
    List<ConstrCupResult> findAll();

    ConstrCupResult save(ConstrCupResult entity);

    void delete(ConstrCupResult entity);

    ConstrCupResult findById(int team, int season);
}