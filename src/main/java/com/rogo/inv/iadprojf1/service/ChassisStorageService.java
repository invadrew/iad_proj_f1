package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;

import java.util.List;

public interface ChassisStorageService {
    List<ChassisStorage> findAll();

    ChassisStorage save(ChassisStorage entity);

    void delete(ChassisStorage entity);

    ChassisStorage findById(int id);
}
