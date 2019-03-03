package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Piloting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotingRepository extends JpaRepository<Piloting, Integer> {
    Piloting findById(int id);

    Piloting findByCarIdAndRacerId(int car, int racer);

}