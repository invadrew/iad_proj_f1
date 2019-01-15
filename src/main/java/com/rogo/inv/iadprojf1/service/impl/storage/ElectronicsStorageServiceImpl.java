package com.rogo.inv.iadprojf1.service.impl.storage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.repository.storageRepository.ElectronicsStorageRepository;
import com.rogo.inv.iadprojf1.service.ElectronicsStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
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
}
