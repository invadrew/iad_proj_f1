package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findById(int id);

    Team findByName(String name);

    List<Team> getAllByStatus(AcceptStatus status);

    @Query(value = "SELECT tm.NAME, tm.surname, u.spec, u.id FROM team_members tm\n" +
            "   INNER JOIN users u ON (u.id = tm.user_id)\n" +
            "   WHERE (tm.team_id = ?1) AND (u.status = 'ACCEPTED');", nativeQuery = true)
    List<Object[]> getTeamTable(int team);

    @Query(value = "SELECT COUNT(*) FROM team_members tm\n" +
            "   INNER JOIN users u ON (u.id = tm.user_id)\n" +
            "   WHERE ((tm.team_id = ?1) AND (u.spec = 'RACER') AND (u.status = 'ACCEPTED'));", nativeQuery = true)
    Integer getRacersCount(int team);

    @Query(value = "SELECT COUNT(*) FROM team_members tm\n" +
            "   INNER JOIN users u ON (u.id = tm.user_id)\n" +
            "   WHERE ((tm.team_id = ?1) AND (u.spec = 'MECHANIC') AND (u.status = 'ACCEPTED'));", nativeQuery = true)
    Integer getMechanicsCount(int team);

    @Query(value = "SELECT COUNT(*) FROM team_members tm\n" +
            "   INNER JOIN users u ON (u.id = tm.user_id)\n" +
            "   WHERE ((tm.team_id = ?1) AND (u.spec = 'MANAGER') AND (u.status = 'ACCEPTED'));", nativeQuery = true)
    Integer getManagersCount(int team);

    @Query(value = "SELECT COUNT(*) FROM team_members tm\n" +
            "   INNER JOIN users u ON (u.id = tm.user_id)\n" +
            "   WHERE ((tm.team_id = ?1) AND (u.spec = 'CONSTRUCTOR') AND (u.status = 'ACCEPTED'));", nativeQuery = true)
    Integer getConstrsCount(int team);

    @Query(value = "SELECT points FROM constr_cup_result c WHERE ((c.season_id = ?1) AND (c.team_id = ?2));", nativeQuery = true)
    Integer getSeasPoints(int season, int team);

    @Query(value = "SELECT MIN(c.place) FROM constr_cup_result c WHERE (c.team_id = ?1); ", nativeQuery = true)
    Integer getBestPlace(int team);

    @Query(value = "SELECT MIN(s), subq.name n, subq.surname s, subq.user_id FROM (\n" +
            "SELECT r.name , r.surname , SUM(wcr.place) AS s, r.user_id FROM team_members r\n" +
            "  INNER JOIN world_cup_result wcr ON (wcr.racer_id = r.user_id)\n" +
            "  WHERE (r.team_id = ?1) GROUP BY r.name,r.surname, r.user_id) subq GROUP BY subq.name, subq.surname, subq.user_id;", nativeQuery = true)
    List<Object[]> bestRacer(int team);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Team t SET t.budget = :budg WHERE t.id = :tId")
    int updTeamBudget(@Param("budg") Double budg, @Param("tId") Integer tId);

    List<Team> getAllBySender(User sender);

}