package com.rogo.inv.iadprojf1.service.impl.storage;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import com.rogo.inv.iadprojf1.repository.storageRepository.EngineStorageRepository;
import com.rogo.inv.iadprojf1.service.EngineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("engineService")
public class EngineStorageServiceImpl implements EngineStorageService {
    @Autowired
    EngineStorageRepository repository;

    @Override
    public List<EngineStorage> findAll() {
        return repository.findAll();
    }

    @Override
    public EngineStorage save(EngineStorage entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(EngineStorage entity) {
        repository.delete(entity);
    }

    @Override
    public EngineStorage findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<EngineStorage> findAllByTeam(Team team) { return repository.findAllByTeam(team);}
}