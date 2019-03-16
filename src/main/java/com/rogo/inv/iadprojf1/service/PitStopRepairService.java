package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;
import com.rogo.inv.iadprojf1.entity.race.Race;

import java.util.List;

public interface PitStopRepairService {
    List<PitStopRepair> findAll();

    PitStopRepair save(PitStopRepair entity);

    void delete(PitStopRepair entity);

    PitStopRepair findById(int id);

    List<PitStopRepair> findAllByCar(Car car);

    List<PitStopRepair> findAllByRaceAndStatusAndTeamId(Race race, AcceptStatus status, Team team);

    List<PitStopRepair> findAllByRaceAndTeamId(Race race, Team team);

    List<PitStopRepair> findAllByRaceAndStatusAndTeamIdAndCar(Race race, AcceptStatus status, Team team, Car car);
}