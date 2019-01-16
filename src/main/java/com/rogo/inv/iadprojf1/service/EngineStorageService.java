package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;

import java.util.List;

public interface EngineStorageService {
    List<EngineStorage> findAll();

    EngineStorage save(EngineStorage entity);

    void delete(EngineStorage entity);

    EngineStorage findById(int id);

    List<EngineStorage> findAllByTeam(Team team);
}
