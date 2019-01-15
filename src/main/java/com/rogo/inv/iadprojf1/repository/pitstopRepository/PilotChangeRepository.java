package com.rogo.inv.iadprojf1.repository.pitstopRepository;

import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotChangeRepository extends JpaRepository<PilotChange, Integer> {
    PilotChange findById(int id);
}