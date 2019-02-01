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

    @Query(value = "select m2.name, m2.surname, r.date_time, c2.name AS ch, c2.season_id, t.name AS tname FROM race_results res \n" +
            "INNER JOIN races r on res.race_id = r.id \n" +
            "INNER JOIN piloting p on res.piloting_id = p.id \n" +
            "INNER JOIN team_members m2 on p.racer_id = m2.user_id \n" +
            "INNER JOIN championships c2 on r.champ_id = c2.id \n" +
            "INNER JOIN teams t on m2.team_id = t.id \n" +
            "WHERE ((res.place = 1) AND (r.duration IS NOT NULL )) ORDER BY r.date_time DESC LIMIT 1;", nativeQuery = true)
    Object[] getRaceNews();

    @Query(value = "SELECT ch.season_id, ch.name, res.place FROM race_results AS res\n" +
            "  INNER JOIN piloting p ON (res.piloting_id = p.id)\n" +
            "  INNER JOIN cars c2 ON (p.car_id = c2.id)\n" +
            "  INNER JOIN teams team ON (c2.team_id = team.id)\n" +
            "  INNER JOIN team_members racers ON (racers.user_id = p.racer_id)\n" +
            "  INNER JOIN races r ON (r.id = res.race_id)\n" +
            "  INNER JOIN championships ch ON (ch.id = r.champ_id)\n" +
            "  WHERE ((racers.user_id = ?1))\n" +
            "  ORDER BY res.place;", nativeQuery = true)
    List<Object[]> getRacerProfileTable(int user_id);

}
