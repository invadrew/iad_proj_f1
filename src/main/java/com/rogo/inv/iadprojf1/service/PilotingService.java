package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Piloting;

import java.util.List;

public interface PilotingService {
    List<Piloting> findAll();

    Piloting save(Piloting entity);

    void delete(Piloting entity);

    Piloting findById(int id);

    Piloting findByCarIdAndRacerId(int car, int racer);
}