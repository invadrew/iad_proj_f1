package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarcaseStorageRepository extends JpaRepository<CarcaseStorage, Integer> {
    CarcaseStorage findById(int id);
}
