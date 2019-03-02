package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectronicsStorageRepository extends JpaRepository<ElectronicsStorage, Integer> {
    ElectronicsStorage findById(int id);

    List<ElectronicsStorage> findAllByTeam(Team team);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE ElectronicsStorage t SET t.condition = 'PERFECT' WHERE t.id = :electronics")
    int repairElectronics( @Param("electronics") Integer electronics);

}
