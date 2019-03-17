package com.rogo.inv.iadprojf1.service.impl.pitstop;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.repository.pitstopRepository.PitStopServiceRepository;
import com.rogo.inv.iadprojf1.service.PitStopServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pitstopServiceService")
public class PitStopServiceServiceImpl implements PitStopServiceService {
    @Autowired
    PitStopServiceRepository repository;

    @Override
    public List<PitStopService> findAll() {
        return repository.findAll();
    }

    @Override
    public PitStopService save(PitStopService entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(PitStopService entity) {
        repository.delete(entity);
    }

    @Override
    public PitStopService findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<PitStopService> findAllByCarAndStatusAndSenderAndRace(Car car, AcceptStatus acceptStatus, String sender, Race race) {
        return repository.findAllByCarAndStatusAndSenderAndRace(car, acceptStatus, sender, race);
    }

    @Override
    public List<PitStopService> findAllByTeamIdAndRaceAndStatus(Team team, Race race, AcceptStatus acceptStatus) {
        return repository.findAllByTeamIdAndRaceAndStatus(team, race, acceptStatus);
    }

    @Override
    public List<PitStopService> findAllByTeamIdAndStatusAndSenderAndRace(Team team, AcceptStatus acceptStatus, String sender, Race race) {
        return repository.findAllByTeamIdAndStatusAndSenderAndRace(team, acceptStatus, sender, race);
    }

    @Override
    public List<PitStopService> findAllByTeamIdAndRace(Team team, Race race) {
        return repository.findAllByTeamIdAndRace(team, race);
    }
}