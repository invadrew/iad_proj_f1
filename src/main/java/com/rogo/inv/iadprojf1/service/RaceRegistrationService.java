package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaceRegistrationService {
    List<RaceRegistration> findAll();

    RaceRegistration save(RaceRegistration entity);

    void delete(RaceRegistration entity);

    RaceRegistration findById(Team team, Race race);

    List<Object[]> getRegistrationTable(int race);

    Integer canReg(int race);

    List<RaceRegistration> findAllByRace(Race race);

    int addNewRegRecord(@Param("team") Integer team, @Param("race") Integer race, @Param("firstP") Integer firstP, @Param("firstC") Integer firstC,
                        @Param("secondP") Integer secondP, @Param("secondC") Integer secondC );

    int updRegRequest(@Param("status") AcceptStatus status, @Param("comment") String comment, @Param("race") Race race, @Param("team") Team team);

    int addNewRegRecordOne(@Param("team") Integer team, @Param("race") Integer race, @Param("firstP") Integer firstP, @Param("firstC") Integer firstC);
}
