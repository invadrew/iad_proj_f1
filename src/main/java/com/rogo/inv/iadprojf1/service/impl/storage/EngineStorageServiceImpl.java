package com.rogo.inv.iadprojf1.service.impl.storage;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import com.rogo.inv.iadprojf1.repository.storageRepository.EngineStorageRepository;
import com.rogo.inv.iadprojf1.service.EngineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override @Transactional
    public int repairEngine( @Param("engine") Integer engine) { return repository.repairEngine(engine); }

    @Override
    public List<EngineStorage> findAllByTeamAndStatus(Team team, AcceptStatus status) { return repository.findAllByTeamAndStatus(team, status); }

    @Override
    public List<EngineStorage> findAllBySender(User sender) { return repository.findAllBySender(sender); }

}