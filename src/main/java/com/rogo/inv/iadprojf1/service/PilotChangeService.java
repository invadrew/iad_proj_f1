package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.entity.race.Race;

import java.util.List;

public interface PilotChangeService {
    List<PilotChange> findAll();

    PilotChange save(PilotChange entity);

    void delete(PilotChange entity);

    PilotChange findById(int id);

    List<PilotChange> findAllByRaceAndStatusAndTeamId(Race race, AcceptStatus status, Team team);
}