package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;
import com.rogo.inv.iadprojf1.entity.race.Race;

import java.util.List;

public interface PitStopServiceService {
    List<PitStopService> findAll();

    PitStopService save(PitStopService entity);

    void delete(PitStopService entity);

    PitStopService findById(int id);

    List<PitStopService> findAllByCarAndStatusAndSenderAndRace(Car car, AcceptStatus acceptStatus, String sender, Race race);

    List<PitStopService> findAllByTeamIdAndRaceAndStatus(Team team, Race race, AcceptStatus acceptStatus);

    List<PitStopService> findAllByTeamIdAndStatusAndSenderAndRace(Team team, AcceptStatus acceptStatus, String sender, Race race);

    List<PitStopService> findAllByTeamIdAndRace(Team team, Race race);
}