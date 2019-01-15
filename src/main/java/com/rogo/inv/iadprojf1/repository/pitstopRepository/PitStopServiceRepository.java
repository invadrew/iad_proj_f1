package com.rogo.inv.iadprojf1.repository.pitstopRepository;

import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitStopServiceRepository extends JpaRepository<PitStopService, Integer> {
    PitStopService findById(int id);
}
