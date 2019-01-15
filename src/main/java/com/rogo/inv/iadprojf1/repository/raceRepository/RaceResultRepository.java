package com.rogo.inv.iadprojf1.repository.raceRepository;

import com.rogo.inv.iadprojf1.entity.race.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Integer> {

    @Query(value = "select r from RaceResult r where r.car = ?1 and r.race = ?2")
    RaceResult findById(@Param("car") int car, @Param("race") int race);

}
