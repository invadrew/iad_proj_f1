package com.rogo.inv.iadprojf1.service.impl.storage;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import com.rogo.inv.iadprojf1.repository.storageRepository.ChassisStorageRepository;
import com.rogo.inv.iadprojf1.service.ChassisStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
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
}
