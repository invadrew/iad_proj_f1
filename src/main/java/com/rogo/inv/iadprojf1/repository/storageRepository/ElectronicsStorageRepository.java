package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicsStorageRepository extends JpaRepository<ElectronicsStorage, Integer> {
    ElectronicsStorage findById(int id);
}
