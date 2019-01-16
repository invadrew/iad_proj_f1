package com.rogo.inv.iadprojf1.repository.raceRepository;

import com.rogo.inv.iadprojf1.entity.race.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Integer> {

    @Query(value = "select r from RaceResult r where r.car = ?1 and r.race = ?2")
    RaceResult findById(@Param("car") int car, @Param("race") int race);

    @Query(value = "SELECT res.place, racers.NAME as rc, racers.surname, team.NAME, res.points, res.race_time FROM race_results AS res\n" +
            "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "  INNER JOIN cars c2 ON (p.car_id = c2.id)\n" +
            "  INNER JOIN teams team ON (c2.team_id = team.id)\n" +
            "  INNER JOIN team_members racers ON (racers.user_id = p.racer_id)\n" +
            "  INNER JOIN races r ON (r.id = res.race_id)\n" +
            "  INNER JOIN championships ch ON (ch.id = r.champ_id)\n" +
            "  WHERE ((ch.season_id = ?1) AND (ch.NAME = ?2))\n" +
            "  ORDER BY res.place;", nativeQuery = true)
    List<Object[]> getResultTable(int season, String champ);
}
