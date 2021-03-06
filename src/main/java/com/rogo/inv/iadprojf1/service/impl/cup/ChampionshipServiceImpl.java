package com.rogo.inv.iadprojf1.service.impl.cup;
import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import com.rogo.inv.iadprojf1.repository.cupRepository.ChampionshipRepository;
import com.rogo.inv.iadprojf1.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("championshipService")
public class ChampionshipServiceImpl implements ChampionshipService {
    @Autowired
    ChampionshipRepository repository;

    @Override
    public List<Championship> findAll() {
        return repository.findAll();
    }

    @Override
    public Championship save(Championship entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Championship entity) {
        repository.delete(entity);
    }

    @Override
    public Championship findById(int id) {
        return repository.findById(id);
    }

    @Override @Transactional
    public List<Championship> getAllBySeason(Season season) {return repository.getAllBySeason(season);};
    //public List<Championship> getAllChamps(int season) {return repository.getAllChamps(season);}
}
