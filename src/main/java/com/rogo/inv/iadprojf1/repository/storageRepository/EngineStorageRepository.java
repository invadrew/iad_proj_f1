package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineStorageRepository extends JpaRepository<EngineStorage, Integer> {
    EngineStorage findById(int id);

    List<EngineStorage> findAllByTeam(Team team);
}
