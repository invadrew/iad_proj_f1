package com.rogo.inv.iadprojf1.repository.raceRepository;

import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRegistrationRepository extends JpaRepository<RaceRegistration, Integer> {

    @Query(value = "select r from RaceRegistration r where r.team = ?1 and r.race = ?2")
    RaceRegistration findById(@Param("team") int team, @Param("race") int race);

}