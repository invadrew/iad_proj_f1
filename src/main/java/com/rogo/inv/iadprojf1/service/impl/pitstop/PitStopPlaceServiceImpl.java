package com.rogo.inv.iadprojf1.service.impl.pitstop;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopPlace;
import com.rogo.inv.iadprojf1.repository.pitstopRepository.PitStopPlaceRepository;
import com.rogo.inv.iadprojf1.service.PitStopPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pitstopPlaceService")
public class PitStopPlaceServiceImpl implements PitStopPlaceService {
    @Autowired
    PitStopPlaceRepository repository;

    @Override
    public List<PitStopPlace> findAll() {
        return repository.findAll();
    }

    @Override
    public PitStopPlace save(PitStopPlace entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(PitStopPlace entity) {
        repository.delete(entity);
    }

    @Override
    public PitStopPlace findById(int id) {
        return repository.findById(id);
    }
}
