package com.rogo.inv.iadprojf1.service.impl.pitstop;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;
import com.rogo.inv.iadprojf1.repository.pitstopRepository.PitStopServiceRepository;
import com.rogo.inv.iadprojf1.service.PitStopServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pitstopServiceService")
public class PitStopServiceServiceImpl implements PitStopServiceService {
    @Autowired
    PitStopServiceRepository repository;

    @Override
    public List<PitStopService> findAll() {
        return repository.findAll();
    }

    @Override
    public PitStopService save(PitStopService entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(PitStopService entity) {
        repository.delete(entity);
    }

    @Override
    public PitStopService findById(int id) {
        return repository.findById(id);
    }
}