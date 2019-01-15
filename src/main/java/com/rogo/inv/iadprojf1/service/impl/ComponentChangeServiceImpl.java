package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.ComponentChange;
import com.rogo.inv.iadprojf1.repository.ComponentChangeRepository;
import com.rogo.inv.iadprojf1.service.ComponentChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class ComponentChangeServiceImpl implements ComponentChangeService {
    @Autowired
    ComponentChangeRepository repository;

    @Override
    public List<ComponentChange> findAll() {
        return repository.findAll();
    }

    @Override
    public ComponentChange save(ComponentChange entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ComponentChange entity) {
        repository.delete(entity);
    }

    @Override
    public ComponentChange findById(int id) {
        return repository.findById(id);
    }
}
