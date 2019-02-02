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

    @Query(value = "SELECT cup.place, team.NAME, cup.points, team.id FROM constr_cup_result AS cup\n" +
            "  INNER JOIN teams AS team ON (team.id = cup.team_id)\n" +
            "  WHERE (cup.season_id = ?1) ORDER BY cup.place;", nativeQuery = true)
    List<Object[]> getConstrCupResultTable(int season);

    @Query(value = "SELECT cup.season_id, cup.place, team.name, cup.points FROM constr_cup_result AS cup\n" +
            "  INNER JOIN teams AS team ON (team.id = cup.team_id)\n" +
            "  ORDER BY cup.season_id DESC, cup.place; ", nativeQuery = true)
    List<Object[]> getRates();

    @Query(value = "SELECT cup.season_id, cup.place FROM constr_cup_result AS cup\n" +
            "  WHERE (cup.team_id =?1)\n" +
            "  ORDER BY cup.season_id DESC;", nativeQuery = true)
    List<Object[]> getProfileAchievs(int team);

    @Query(value = "SELECT place FROM constr_cup_result c WHERE ((c.season_id = ?1) AND (c.team_id = ?2));", nativeQuery = true)
    int getPlaceSeason(int season, int team);
}