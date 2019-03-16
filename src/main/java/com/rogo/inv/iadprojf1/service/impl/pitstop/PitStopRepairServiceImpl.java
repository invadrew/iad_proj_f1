package com.rogo.inv.iadprojf1.service.impl.pitstop;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.repository.pitstopRepository.PitStopRepairRepository;
import com.rogo.inv.iadprojf1.service.PitStopRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pitstopRepairService")
public class PitStopRepairServiceImpl implements PitStopRepairService {
    @Autowired
    PitStopRepairRepository repository;

    @Override
    public List<PitStopRepair> findAll() {
        return repository.findAll();
    }

    @Override
    public PitStopRepair save(PitStopRepair entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(PitStopRepair entity) {
        repository.delete(entity);
    }

    @Override
    public PitStopRepair findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<PitStopRepair> findAllByCar(Car car) { return repository.findAllByCar(car); }

    @Override
    public  List<PitStopRepair> findAllByRaceAndStatusAndTeamId(Race race, AcceptStatus status, Team team) {
        return repository.findAllByRaceAndStatusAndTeamId(race, status, team); }

    @Override
    public List<PitStopRepair> findAllByRaceAndTeamId(Race race, Team team) {
        return repository.findAllByRaceAndTeamId(race, team);
    }

}