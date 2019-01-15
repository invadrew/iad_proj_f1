package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;

import java.util.List;

public interface ElectronicsStorageService {
    List<ElectronicsStorage> findAll();

    ElectronicsStorage save(ElectronicsStorage entity);

    void delete(ElectronicsStorage entity);

    ElectronicsStorage findById(int id);
}
