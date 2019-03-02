package com.rogo.inv.iadprojf1.service.impl.storage;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.repository.storageRepository.ElectronicsStorageRepository;
import com.rogo.inv.iadprojf1.service.ElectronicsStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("electronicsService")
public class ElectronicsStorageServiceImpl implements ElectronicsStorageService {
    @Autowired
    ElectronicsStorageRepository repository;

    @Override
    public List<ElectronicsStorage> findAll() {
        return repository.findAll();
    }

    @Override
    public ElectronicsStorage save(ElectronicsStorage entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ElectronicsStorage entity) {
        repository.delete(entity);
    }

    @Override
    public ElectronicsStorage findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<ElectronicsStorage> findAllByTeam(Team team) { return repository.findAllByTeam(team);}

    @Override @Transactional
    public int repairElectronics( @Param("electronics") Integer electronics) { return repository.repairElectronics(electronics); }
}
