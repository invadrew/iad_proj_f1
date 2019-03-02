package com.rogo.inv.iadprojf1.service.impl.storage;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.repository.storageRepository.ChassisStorageRepository;
import com.rogo.inv.iadprojf1.service.ChassisStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("chassisService")
public class ChassisStorageServiceImpl implements ChassisStorageService {
    @Autowired
    ChassisStorageRepository repository;

    @Override
    public List<ChassisStorage> findAll() {
        return repository.findAll();
    }

    @Override
    public ChassisStorage save(ChassisStorage entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ChassisStorage entity) {
        repository.delete(entity);
    }

    @Override
    public ChassisStorage findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<ChassisStorage> findAllByTeam(Team team) { return repository.findAllByTeam(team);}

    @Override @Transactional
    public int repairChassis( @Param("chassis") Integer chassis) { return repository.repairChassis(chassis); }
}
