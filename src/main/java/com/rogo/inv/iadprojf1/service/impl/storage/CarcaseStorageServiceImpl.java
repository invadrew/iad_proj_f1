package com.rogo.inv.iadprojf1.service.impl.storage;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.repository.storageRepository.CarcaseStorageRepository;
import com.rogo.inv.iadprojf1.service.CarcaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class CarcaseStorageServiceImpl implements CarcaseStorageService {
    @Autowired
    CarcaseStorageRepository repository;

    @Override
    public List<CarcaseStorage> findAll() {
        return repository.findAll();
    }

    @Override
    public CarcaseStorage save(CarcaseStorage entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(CarcaseStorage entity) {
        repository.delete(entity);
    }

    @Override
    public CarcaseStorage findById(int id) {
        return repository.findById(id);
    }
}
