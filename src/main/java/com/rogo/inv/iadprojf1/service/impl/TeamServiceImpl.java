package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.repository.TeamRepository;
import com.rogo.inv.iadprojf1.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository repository;

    @Override
    public List<Team> findAll() {
        return repository.findAll();
    }

    @Override
    public Team save(Team entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Team entity) {
        repository.delete(entity);
    }

    @Override
    public Team findById(int id) {
        return repository.findById(id);
    }
}