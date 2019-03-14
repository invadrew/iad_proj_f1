package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopTransfer;
import com.rogo.inv.iadprojf1.entity.race.Race;

import java.util.List;

public interface PitStopTransferService {
    List<PitStopTransfer> findAll();

    PitStopTransfer save(PitStopTransfer entity);

    void delete(PitStopTransfer entity);

    PitStopTransfer findById(int id);

    List<PitStopTransfer> findAllByStatusAndRace(AcceptStatus status, Race race);

    List<PitStopTransfer> findAllByTransferAndRace(PitStopTransfer.Transfers transfers, Race race);
}