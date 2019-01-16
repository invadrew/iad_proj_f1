package com.rogo.inv.iadprojf1.repository.pitstopRepository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitStopPlaceRepository extends JpaRepository<PitStopPlace, Integer> {
    PitStopPlace findById(int id);

    List<PitStopPlace> findAllByTeam(Team team);
}