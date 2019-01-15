package com.rogo.inv.iadprojf1.service.impl.cup;
import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;
import com.rogo.inv.iadprojf1.repository.cupRepository.WorldCupResultRepository;
import com.rogo.inv.iadprojf1.service.WorldCupResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("worldCupService")
public class WorldCupResultServiceImpl implements WorldCupResultService {
    @Autowired
    WorldCupResultRepository repository;

    @Override
    public List<WorldCupResult> findAll() {
        return repository.findAll();
    }

    @Override
    public WorldCupResult save(WorldCupResult entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(WorldCupResult entity) {
        repository.delete(entity);
    }

    @Override
    public WorldCupResult findById(int racer, int season) {
        return repository.findById(racer, season);
    }
}