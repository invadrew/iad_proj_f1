package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import com.rogo.inv.iadprojf1.repository.SeasonRepository;
import com.rogo.inv.iadprojf1.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("seasonService")
public class SeasonServiceImpl implements SeasonService {
    @Autowired
    SeasonRepository repository;

    @Override
    public List<Season> findAll() {
        return repository.findAll();
    }

    @Override
    public Season save(Season entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Season entity) {
        repository.delete(entity);
    }

    @Override
    public Season findByYear(int id) {
        return repository.findByYear(id);
    }


}
