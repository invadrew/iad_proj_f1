package com.rogo.inv.iadprojf1.repository.cupRepository;

import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorldCupResultRepository extends JpaRepository<WorldCupResult, Integer> {

    @Query(value = "select ch from WorldCupResult ch where ch.racer = ?1 and ch.season = ?2")
    WorldCupResult findById(@Param("racer") int racer, @Param("season") int season);

    @Query(value = "SELECT cup.place, racers.name as rac, racers.surname, team.name, cup.points, racers.user_id, team.id FROM world_cup_result AS cup\n" +
            "  INNER JOIN team_members AS racers  ON (racers.user_id = cup.racer_id)\n" +
            "  INNER JOIN teams AS team ON (team.id = racers.team_id)\n" +
            "  WHERE (cup.season_id = ?1) ORDER BY cup.place;", nativeQuery = true
    )
    List<Object[]> getResTable(@Param("season") int season);
}