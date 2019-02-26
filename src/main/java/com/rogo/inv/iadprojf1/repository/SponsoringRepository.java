package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Sponsoring;
import com.rogo.inv.iadprojf1.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface SponsoringRepository extends JpaRepository<Sponsoring, Integer> {
    Sponsoring findById(int id);

    @Query(value = "SELECT COUNT( DISTINCT sp.team_id) FROM sponsoring sp WHERE (sp.sponsor_id = ?1);", nativeQuery = true)
    Integer getTeamCount(int sponsor);

    @Query(value = "SELECT SUM(sp.sp_money) FROM sponsoring sp WHERE (sp.sponsor_id = ?1);", nativeQuery = true)
    Integer getSumMoney(int sponsor);

    @Query(value = "SELECT SUM(sp.sp_money) FROM sponsoring sp WHERE ((sp.sponsor_id = ?1) AND (sp.team_id = ?2));", nativeQuery = true)
    Integer getSumMoneyForTeam(int sponsor, int team);

    @Query(value = "SELECT DISTINCT sp.team_id FROM sponsoring sp WHERE (sp.sponsor_id = ?1);", nativeQuery = true)
    List<Object[]> getSponsInfo(int team);

    List<Sponsoring> findAllByTeamAndSponsor(Team team, Sponsor sponsor);

    @Query(value = "SELECT t.name as teamN, s2.name, sp.sp_money, sp.date FROM sponsoring sp \n" +
            "INNER JOIN sponsors s2 on sp.sponsor_id = s2.user_id \n" +
            "INNER JOIN teams t on sp.team_id = t.id \n" +
            "ORDER BY sp.date DESC LIMIT 1;",nativeQuery = true)
    Object[] getLatestSponsNews();

}