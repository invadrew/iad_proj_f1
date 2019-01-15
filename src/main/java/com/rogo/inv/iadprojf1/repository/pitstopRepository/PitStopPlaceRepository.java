package com.rogo.inv.iadprojf1.repository.pitstopRepository;

import com.rogo.inv.iadprojf1.entity.pitstop.PitStopPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitStopPlaceRepository extends JpaRepository<PitStopPlace, Integer> {
    PitStopPlace findById(int id);
}