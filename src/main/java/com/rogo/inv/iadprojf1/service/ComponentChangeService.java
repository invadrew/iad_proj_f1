package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.ComponentChange;

import java.util.List;

public interface ComponentChangeService {
    List<ComponentChange> findAll();

    ComponentChange save(ComponentChange entity);

    void delete(ComponentChange entity);

    ComponentChange findById(int id);
}