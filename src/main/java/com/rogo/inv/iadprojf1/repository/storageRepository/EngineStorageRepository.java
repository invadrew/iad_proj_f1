package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineStorageRepository extends JpaRepository<EngineStorage, Integer> {
    EngineStorage findById(int id);
}
