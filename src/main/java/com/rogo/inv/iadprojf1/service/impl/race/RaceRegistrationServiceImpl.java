package com.rogo.inv.iadprojf1.service.impl.race;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import com.rogo.inv.iadprojf1.repository.raceRepository.RaceRegistrationRepository;
import com.rogo.inv.iadprojf1.service.RaceRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RaceRegistration save(RaceRegistration entity) {
        return repository.save(entity);
    }

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
}
