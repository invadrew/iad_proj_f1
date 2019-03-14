package com.rogo.inv.iadprojf1.repository.pitstopRepository;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopTransfer;
import com.rogo.inv.iadprojf1.entity.race.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitStopTransferRepository extends JpaRepository<PitStopTransfer, Integer> {
    PitStopTransfer findById(int id);

    List<PitStopTransfer> findAllByStatusAndRace(AcceptStatus status, Race race);

    List<PitStopTransfer> findAllByTransferAndRace(PitStopTransfer.Transfers transfers, Race race);

}