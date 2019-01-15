package com.rogo.inv.iadprojf1.repository.pitstopRepository;

import com.rogo.inv.iadprojf1.entity.pitstop.PitStopTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitStopTransferRepository extends JpaRepository<PitStopTransfer, Integer> {
    PitStopTransfer findById(int id);
}