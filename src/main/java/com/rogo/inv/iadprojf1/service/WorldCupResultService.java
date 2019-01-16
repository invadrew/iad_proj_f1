package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;

import java.util.List;

public interface WorldCupResultService {
    List<WorldCupResult> findAll();

    WorldCupResult save(WorldCupResult entity);

    void delete(WorldCupResult entity);

    WorldCupResult findById(int racer, int season);

    List<Object[]> getResTable(int season);


}
