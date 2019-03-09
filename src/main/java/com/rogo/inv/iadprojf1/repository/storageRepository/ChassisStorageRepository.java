package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChassisStorageRepository extends JpaRepository<ChassisStorage, Integer> {
    ChassisStorage findById(int id);

    List<ChassisStorage> findAllByTeam(Team team);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE ChassisStorage t SET t.condition = 'PERFECT' WHERE t.id = :chassis")
    int repairChassis( @Param("chassis") Integer chassis);

    List<ChassisStorage> findAllByTeamAndStatus(Team team, AcceptStatus status);

    List<ChassisStorage> findAllBySender(User sender);
}
