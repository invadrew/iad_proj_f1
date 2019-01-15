package com.rogo.inv.iadprojf1.repository.cupRepository;

import com.rogo.inv.iadprojf1.entity.cup.ConstrCupResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstrCupResultRepository extends JpaRepository<ConstrCupResult, Integer> {

    @Query(value = "select ch from ConstrCupResult ch where ch.team = ?1 and ch.season = ?2")
    ConstrCupResult findById(@Param("team") int team, @Param("season") int season);
}