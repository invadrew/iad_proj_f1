package com.rogo.inv.iadprojf1.repository.raceRepository;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceRegistrationRepository extends JpaRepository<RaceRegistration, Integer> {

    @Query(value = "select r from RaceRegistration r where r.team = ?1 and r.race = ?2")
    RaceRegistration findById(@Param("team") Team team, @Param("race") Race race);

    @Query(value = "SELECT racer.NAME rc, racer.surname, team.NAME AS tean_name, car.label, car.model, racer.user_id, team.id FROM race_registration race\n" +
            "INNER JOIN team_members racer ON (race.first_pilot = racer.user_id)\n" +
            "INNER JOIN cars car ON (race.first_car = car.id)\n" +
            "INNER JOIN teams team ON (racer.team_id = team.id)\n" +
            "WHERE ((race.status = 'ACCEPTED') AND (race.race_id = ?1))\n" +
            " UNION\n" +
            "SELECT racer.NAME , racer.surname, team.NAME AS tean_name, car.label, car.model, racer.user_id, team.id FROM race_registration race\n" +
            "INNER JOIN team_members racer ON (race.second_pilot = racer.user_id)\n" +
            "INNER JOIN cars car ON (race.second_car = car.id)\n" +
            "INNER JOIN teams team ON (racer.team_id = team.id)\n" +
            "WHERE ((race.status = 'ACCEPTED') AND (race.race_id = ?1));", nativeQuery = true)
    List<Object[]> getRegistrationTable(int race);

    @Query(value = "SELECT COUNT(*) FROM race_registration AS registration \n" +
            "WHERE registration.race_id = ?1", nativeQuery = true)
    Integer canReg(int race);

}