package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("seasonService")
public class SeasonServiceRepo implements SeasonService {
    @Autowired
    SeasonRepository repository;

    @Override
    public List<Season> findAll() {
        return repository.findAll();
    }


}
