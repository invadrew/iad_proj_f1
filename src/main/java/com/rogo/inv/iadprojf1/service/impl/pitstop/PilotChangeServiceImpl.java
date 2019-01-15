package com.rogo.inv.iadprojf1.service.impl.pitstop;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.repository.pitstopRepository.PilotChangeRepository;
import com.rogo.inv.iadprojf1.service.PilotChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pilotChangeService")
public class PilotChangeServiceImpl implements PilotChangeService {
    @Autowired
    PilotChangeRepository repository;

    @Override
    public List<PilotChange> findAll() {
        return repository.findAll();
    }

    @Override
    public PilotChange save(PilotChange entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(PilotChange entity) {
        repository.delete(entity);
    }

    @Override
    public PilotChange findById(int id) {
        return repository.findById(id);
    }
}