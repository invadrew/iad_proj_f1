package com.rogo.inv.iadprojf1.repository.cupRepository;

import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldCupResultRepository extends JpaRepository<WorldCupResult, Integer> {

    @Query(value = "select ch from WorldCupResult ch where ch.racer = ?1 and ch.season = ?2")
    WorldCupResult findById(@Param("racer") int racer, @Param("season") int season);
}