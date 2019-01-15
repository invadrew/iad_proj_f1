package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;

import java.util.List;

public interface PitStopRepairService {
    List<PitStopRepair> findAll();

    PitStopRepair save(PitStopRepair entity);

    void delete(PitStopRepair entity);

    PitStopRepair findById(int id);
}