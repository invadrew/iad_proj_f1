package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.pitstop.PitStopTransfer;

import java.util.List;

public interface PitStopTransferService {
    List<PitStopTransfer> findAll();

    PitStopTransfer save(PitStopTransfer entity);

    void delete(PitStopTransfer entity);

    PitStopTransfer findById(int id);
}