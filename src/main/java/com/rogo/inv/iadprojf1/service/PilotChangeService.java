package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;

import java.util.List;

public interface PilotChangeService {
    List<PilotChange> findAll();

    PilotChange save(PilotChange entity);

    void delete(PilotChange entity);

    PilotChange findById(int id);
}