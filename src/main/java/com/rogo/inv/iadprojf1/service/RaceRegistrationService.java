package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;

import java.util.List;

public interface RaceRegistrationService {
    List<RaceRegistration> findAll();

    RaceRegistration save(RaceRegistration entity);

    void delete(RaceRegistration entity);

    RaceRegistration findById(Team team, Race race);

    List<Object[]> getRegistrationTable(int race);

    Integer canReg(int race);
}
