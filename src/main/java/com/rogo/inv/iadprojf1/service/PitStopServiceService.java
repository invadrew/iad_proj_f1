package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;

import java.util.List;

public interface PitStopServiceService {
    List<PitStopService> findAll();

    PitStopService save(PitStopService entity);

    void delete(PitStopService entity);

    PitStopService findById(int id);
}