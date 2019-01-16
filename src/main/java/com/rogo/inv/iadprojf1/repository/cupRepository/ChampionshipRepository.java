package com.rogo.inv.iadprojf1.repository.cupRepository;

import com.rogo.inv.iadprojf1.entity.cup.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {
    Championship findById(int id);

    @Query(value = "select * from championships ch where (ch.season_id = ?1); ", nativeQuery = true)
    List<Championship> getAllChamps(int season);
}
