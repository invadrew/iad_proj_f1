package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;

import java.util.List;

public interface RaceRegistrationService {
    List<RaceRegistration> findAll();

    RaceRegistration save(RaceRegistration entity);

    void delete(RaceRegistration entity);

    RaceRegistration findById(int team, int race);
}
