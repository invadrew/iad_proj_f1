package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopPlace;

import java.util.List;

public interface PitStopPlaceService {
    List<PitStopPlace> findAll();

    PitStopPlace save(PitStopPlace entity);

    void delete(PitStopPlace entity);

    PitStopPlace findById(int id);

    List<PitStopPlace> findAllByTeam(Team team);
}
