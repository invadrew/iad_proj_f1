package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EngineStorageService {
    List<EngineStorage> findAll();

    EngineStorage save(EngineStorage entity);

    void delete(EngineStorage entity);

    EngineStorage findById(int id);

    List<EngineStorage> findAllByTeam(Team team);

    int repairEngine( @Param("engine") Integer engine);

    List<EngineStorage> findAllByTeamAndStatus(Team team, AcceptStatus status);

    List<EngineStorage> findAllBySender(User sender);
}
