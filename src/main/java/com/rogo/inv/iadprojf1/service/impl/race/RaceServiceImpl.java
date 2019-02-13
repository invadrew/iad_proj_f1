package com.rogo.inv.iadprojf1.service.impl.race;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.repository.raceRepository.RaceRepository;
import com.rogo.inv.iadprojf1.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("raceService")
public class RaceServiceImpl implements RaceService {
    @Autowired
    RaceRepository repository;

    @Override
    public List<Race> findAll() {
        return repository.findAll();
    }

    @Override
    public Race save(Race entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Race entity) {
        repository.delete(entity);
    }

    @Override
    public Race findById(int id) {
        return repository.findById(id);
    }

    @Override
    public  List<Object[]> getCurrentEvent() { return repository.getCurrentEvent();}

    @Override
    public  Race findTopByOrderByDateTimeDesc() { return repository.findTopByOrderByDateTimeDesc();}
}
