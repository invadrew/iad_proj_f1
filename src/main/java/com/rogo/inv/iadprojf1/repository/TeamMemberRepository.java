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

   @Query(value = "SELECT SUM(res.points) FROM race_results res\n" +
           "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
           "  INNER JOIN team_members m ON (p.racer_id = m.user_id)\n" +
           "  INNER JOIN races r ON (res.race_id = r.id)\n" +
           "  INNER JOIN championships c ON (r.champ_id = c.id)\n" +
           "  WHERE ((m.user_id = ?1) AND (c.season_id = ?2));", nativeQuery = true)
   Integer pointsCount(int user, int season);

    @Query(value = "SELECT COUNT(*) FROM race_results res\n" +
            "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "  INNER JOIN team_members m ON (p.racer_id = m.user_id)\n" +
            "  INNER JOIN races r ON (res.race_id = r.id)\n" +
            "  INNER JOIN championships c ON (r.champ_id = c.id)\n" +
            "  WHERE ((m.user_id = ?1) AND (c.season_id = ?2)); ", nativeQuery = true)
   Integer racingsCount(int user, int season);

    @Query(value = "SELECT COUNT(*) FROM race_results res\n" +
            "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "  INNER JOIN team_members m ON (p.racer_id = m.user_id)\n" +
            "  INNER JOIN races r ON (res.race_id = r.id)\n" +
            "  WHERE (m.user_id = ?1); ", nativeQuery = true)
   Integer allRaceCount(int user);

    @Query(value = "SELECT COUNT(*) FROM world_cup_result wcr\n" +
            "  INNER JOIN users u ON (wcr.racer_id = u.id)\n" +
            "  WHERE ((u.id = ?1) AND (wcr.place = 1)); ", nativeQuery = true)
   Integer cupsWon (int user);

    @Query(value = "SELECT COUNT(*) FROM championships\n" +
            "  INNER JOIN races r ON (championships.id = r.champ_id)\n" +
            "  INNER JOIN race_results res ON (r.id = res.race_id)\n" +
            "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "  INNER JOIN team_members m ON (p.racer_id = m.user_id)\n" +
            "   WHERE (m.user_id = ?1);", nativeQuery = true)
   Integer champCount (int user);

    @Query(value = "SELECT AVG(res.place) FROM race_results res\n" +
            "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "  INNER JOIN team_members m ON (p.racer_id = m.user_id)\n" +
            "  WHERE (m.user_id = ?1);", nativeQuery = true)
   Integer avergePlaceAtAll (int user);

    @Query(value = " SELECT AVG(res.place) FROM race_results res\n" +
            "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "  INNER JOIN team_members m ON (p.racer_id = m.user_id)\n" +
            "  INNER JOIN races r ON (res.race_id = r.id)\n" +
            "  INNER JOIN championships c ON (r.champ_id = c.id)\n" +
            "  WHERE ((m.user_id = ?1) AND (c.season_id = ?2)); ", nativeQuery = true)
   Integer avergePlaceAtSeason (int user, int season);

    @Query(value = "SELECT MIN(res.place) FROM race_results res\n" +
            "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "  INNER JOIN team_members m ON (p.racer_id = m.user_id)\n" +
            "  INNER JOIN races r ON (res.race_id = r.id)\n" +
            "  INNER JOIN championships c ON (r.champ_id = c.id)\n" +
            "  WHERE ((m.user_id = ?1) AND (c.season_id = ?2)); ", nativeQuery = true)
   Integer bestPlace (int user, int season);

    @Query(value = "SELECT MIN(res.race_time), r.track FROM race_results res\n" +
            "   INNER JOIN races r ON (res.race_id = r.id)\n" +
            "   INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "   INNER JOIN team_members m ON (p.racer_id = m.user_id)\n" +
            "   WHERE (m.user_id = ?1) GROUP BY r.track; ", nativeQuery = true)
   List<Object[]> getBestTrackTime(int user);
}
