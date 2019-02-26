package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {
    Sponsor findByUserId(int id);

    @Query(value = "SELECT DISTINCT sp.name, sp.user_id FROM sponsors sp \n" +
            "INNER JOIN sponsoring s2 on sp.user_id = s2.sponsor_id \n" +
            "INNER JOIN teams t on s2.team_id = t.id \n" +
            "WHERE t.id = ?1", nativeQuery = true)
    List<Object[]> getTeamSponsorsList(int team);

    @Query(value = "SELECT DISTINCT t.name, t.id FROM teams t \n" +
            "INNER JOIN sponsoring s2 on t.id = s2.team_id \n" +
            "INNER JOIN sponsors s3 on s2.sponsor_id = s3.user_id \n" +
            "WHERE s3.user_id = ?1", nativeQuery = true)
    List<Object[]> getTeamsBySponsor(int sponsor);
}
