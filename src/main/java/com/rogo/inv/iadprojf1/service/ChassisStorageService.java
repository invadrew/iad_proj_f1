package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChassisStorageService {
    List<ChassisStorage> findAll();

    ChassisStorage save(ChassisStorage entity);

    void delete(ChassisStorage entity);

    ChassisStorage findById(int id);

    List<ChassisStorage> findAllByTeam(Team team);

    int repairChassis( @Param("chassis") Integer chassis);
}
