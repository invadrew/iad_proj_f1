package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;

import java.util.List;

public interface CarcaseStorageService {
    List<CarcaseStorage> findAll();

    CarcaseStorage save(CarcaseStorage entity);

    void delete(CarcaseStorage entity);

    CarcaseStorage findById(int id);
}