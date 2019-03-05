package com.rogo.inv.iadprojf1.repository.raceRepository;

import com.rogo.inv.iadprojf1.entity.race.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Integer> {
    Race findById(int id);

    @Query (value = "SELECT c2.season_id, c2.name, c2.stage_num, race.date_time, race.track, race.max_participants, race.id FROM races AS race\n"+
            " INNER JOIN championships AS c2 on race.champ_id = c2.id \n" +
            "ORDER BY race.date_time DESC LIMIT 1;", nativeQuery = true)
    List<Object[]> getCurrentEvent();

    @Query (value = "SELECT stage_num FROM championships AS ch \n" +
            "WHERE ch.season_id = ?1 ORDER BY stage_num DESC LIMIT 1", nativeQuery = true)
    Integer getStageNum(int year);

    Race findTopByOrderByDateTimeDesc();

}