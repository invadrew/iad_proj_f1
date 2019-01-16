package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findById(int id);

    Team findByName(String name);

    @Query(value = "SELECT tm.NAME, tm.surname, u.spec FROM team_members tm\n" +
            "   INNER JOIN users u ON (u.id = tm.user_id)\n" +
            "   WHERE (tm.team_id = ?1);", nativeQuery = true)
    List<Object[]> getTeamTable(int team);

    @Query(value = "SELECT COUNT(*) FROM team_members tm\n" +
            "   INNER JOIN users u ON (u.id = tm.user_id)\n" +
            "   WHERE ((tm.team_id = ?1) AND (u.spec = ?2));", nativeQuery = true)
    int getSpecCount(int team, User.Spec spec);

    @Query(value = "SELECT points FROM constr_cup_result c WHERE ((c.season_id = ?1) AND (c.team_id = ?2));", nativeQuery = true)
    int getSeasPoints(int season, int team);

    @Query(value = "SELECT MIN(c.place) FROM constr_cup_result c WHERE (c.team_id = ?1); ", nativeQuery = true)
    int getBestPlace(int team);

    @Query(value = "SELECT MIN(s), subq.name n, subq.surname s FROM (\n" +
            "SELECT r.name , r.surname , SUM(wcr.place) AS s FROM team_members r\n" +
            "  INNER JOIN world_cup_result wcr ON (wcr.racer_id = r.user_id)\n" +
            "  WHERE (r.team_id = ?1) GROUP BY r.name,r.surname) subq GROUP BY subq.name, subq.surname;", nativeQuery = true)
    int bestRacer(int team);

}
