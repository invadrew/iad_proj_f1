package com.rogo.inv.iadprojf1.repository.cupRepository;

import com.rogo.inv.iadprojf1.entity.cup.ConstrCupResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstrCupResultRepository extends JpaRepository<ConstrCupResult, Integer> {

    @Query(value = "select ch from ConstrCupResult ch where ch.team = ?1 and ch.season = ?2")
    ConstrCupResult findById(@Param("team") int team, @Param("season") int season);

    @Query(value = "SELECT cup.place, team.NAME, cup.points FROM constr_cup_result AS cup\n" +
            "  INNER JOIN teams AS team ON (team.id = cup.team_id)\n" +
            "  WHERE (cup.season_id = ?1) ORDER BY cup.place;", nativeQuery = true)
    List<Object[]> getConstrCupResultTable(int season);
}