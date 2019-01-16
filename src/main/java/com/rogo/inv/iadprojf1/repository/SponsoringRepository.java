package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Sponsoring;
import com.rogo.inv.iadprojf1.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsoringRepository extends JpaRepository<Sponsoring, Integer> {
    Sponsoring findById(int id);

    @Query(value = "SELECT COUNT( DISTINCT sp.team_id) FROM sponsoring sp WHERE (sp.sponsor_id = ?1);", nativeQuery = true)
    int getTeamCount(int sponsor);

    @Query(value = "SELECT SUM(sp.sp_money) FROM sponsoring sp WHERE (sp.sponsor_id = ?1);", nativeQuery = true)
    int getSumMoney(int sponsor);

    @Query(value = "SELECT DISTINCT sp.team_id FROM sponsoring sp WHERE (sp.sponsor_id = ?1);", nativeQuery = true)
    List<Object[]> getSponsInfo(int team);

    List<Sponsoring> findAllByTeamAndSponsor(Team team, Sponsor sponsor);

}