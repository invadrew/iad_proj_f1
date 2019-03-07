package com.rogo.inv.iadprojf1.repository.raceRepository;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

    List<RaceRegistration> findAllByRace(Race race);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO race_registration(team_id, race_id, first_pilot, first_car, second_pilot, second_car, status) VALUES \n" +
            "( :team, :race, :firstP, :firstC, :secondP, :secondC, 'ON_REVIEW' )", nativeQuery = true)
    int addNewRegRecord(@Param("team") Integer team, @Param("race") Integer race, @Param("firstP") Integer firstP, @Param("firstC") Integer firstC,
                        @Param("secondP") Integer secondP, @Param("secondC") Integer secondC );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO race_registration(team_id, race_id, first_pilot, first_car, second_pilot, second_car, status) VALUES \n" +
            "( :team, :race, :firstP, :firstC, null , null ,'ON_REVIEW' )", nativeQuery = true)
    int addNewRegRecordOne(@Param("team") Integer team, @Param("race") Integer race, @Param("firstP") Integer firstP, @Param("firstC") Integer firstC);


    @Modifying(clearAutomatically = true)
    @Query("UPDATE RaceRegistration reg SET reg.status = :status, reg.comment = :comment WHERE reg.race = :race AND reg.team = :team")
    int updRegRequest(@Param("status") AcceptStatus status, @Param("comment") String comment, @Param("race") Race race, @Param("team") Team team);
}