package com.rogo.inv.iadprojf1.service.impl.race;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import com.rogo.inv.iadprojf1.repository.raceRepository.RaceRegistrationRepository;
import com.rogo.inv.iadprojf1.service.RaceRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("raceRegistrationService")
public class RaceRegistrationServiceImpl implements RaceRegistrationService {
    @Autowired
    RaceRegistrationRepository repository;

    @Override
    public List<RaceRegistration> findAll() {
        return repository.findAll();
    }

    @Override
    public RaceRegistration save(RaceRegistration entity) { return repository.save(entity); }

    @Override
    public void delete(RaceRegistration entity) {
        repository.delete(entity);
    }

    @Override
    public RaceRegistration findById(Team team, Race race) {
        return repository.findById(team, race);
    }

    @Override
    public List<Object[]> getRegistrationTable(int race) { return repository.getRegistrationTable(race); }

    @Override
    public Integer canReg(int race) { return repository.canReg(race); }

    @Override
    public  List<RaceRegistration> findAllByRace(Race race) { return repository.findAllByRace(race); }

    @Transactional @Override
    public int addNewRegRecord(@Param("team") Integer team, @Param("race") Integer race, @Param("firstP") Integer firstP, @Param("firstC") Integer firstC,
                               @Param("secondP") Integer secondP, @Param("secondC") Integer secondC ) {
        return repository.addNewRegRecord(team, race, firstP, firstC, secondP, secondC);
    }

    @Override @Transactional
    public int updRegRequest(@Param("status") AcceptStatus status, @Param("comment") String comment, @Param("race") Race race, @Param("team") Team team) {
        return repository.updRegRequest(status, comment, race, team);
    }

    @Transactional @Override
    public int addNewRegRecordOne(@Param("team") Integer team, @Param("race") Integer race, @Param("firstP") Integer firstP, @Param("firstC") Integer firstC) {
        return repository.addNewRegRecordOne(team, race, firstP, firstC);
    }
}
