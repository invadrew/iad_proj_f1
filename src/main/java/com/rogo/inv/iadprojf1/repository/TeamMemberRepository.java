package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer> {
    TeamMember findByUserId(int id);

    @Query(value = "SELECT user_id, NAME, surname FROM team_members tm\n" +
            "  INNER JOIN users u ON (tm.user_id = u.id)\n" +
            "  WHERE ((tm.team_id = ?1) AND (u.spec = ?2));", nativeQuery = true)
    List<TeamMember> getAllspecificType(int team, User.Spec spec);

   List<TeamMember> findAllByTeam (Team team);

}
