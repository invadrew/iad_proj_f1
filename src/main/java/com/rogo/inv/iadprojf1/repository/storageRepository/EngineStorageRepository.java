package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineStorageRepository extends JpaRepository<EngineStorage, Integer> {
    EngineStorage findById(int id);

    List<EngineStorage> findAllByTeam(Team team);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE EngineStorage t SET t.condition = 'PERFECT' WHERE t.id = :engine")
    int repairEngine( @Param("engine") Integer engine);
}
