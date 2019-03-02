package com.rogo.inv.iadprojf1.repository.storageRepository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarcaseStorageRepository extends JpaRepository<CarcaseStorage, Integer> {
    CarcaseStorage findById(int id);

    List<CarcaseStorage> findAllByTeam(Team team);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE CarcaseStorage t SET t.condition = 'PERFECT' WHERE t.id = :carcase")
    int repairCarcase( @Param("carcase") Integer carcase);
}
