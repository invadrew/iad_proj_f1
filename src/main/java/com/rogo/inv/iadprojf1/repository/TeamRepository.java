package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
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

}
