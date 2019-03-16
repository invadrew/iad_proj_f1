package com.rogo.inv.iadprojf1.repository.pitstopRepository;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.entity.race.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PilotChangeRepository extends JpaRepository<PilotChange, Integer> {
    PilotChange findById(int id);

    List<PilotChange> findAllByRaceAndStatusAndTeamId(Race race, AcceptStatus status, Team team);

}