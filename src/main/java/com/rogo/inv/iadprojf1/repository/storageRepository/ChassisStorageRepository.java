package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChassisStorageRepository extends JpaRepository<ChassisStorage, Integer> {
    ChassisStorage findById(int id);
}
