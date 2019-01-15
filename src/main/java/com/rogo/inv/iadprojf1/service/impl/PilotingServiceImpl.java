package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Piloting;
import com.rogo.inv.iadprojf1.repository.PilotingRepository;
import com.rogo.inv.iadprojf1.service.PilotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class PilotingServiceImpl implements PilotingService {
    @Autowired
    PilotingRepository repository;

    @Override
    public List<Piloting> findAll() {
        return repository.findAll();
    }

    @Override
    public Piloting save(Piloting entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Piloting entity) {
        repository.delete(entity);
    }

    @Override
    public Piloting findById(int id) {
        return repository.findById(id);
    }
}