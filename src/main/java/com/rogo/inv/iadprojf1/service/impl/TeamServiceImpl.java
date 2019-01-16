package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.repository.TeamRepository;
import com.rogo.inv.iadprojf1.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public Team findByName(String name) { return repository.findByName(name); }

    @Override
    public List<Object[]> getTeamTable(int team) { return repository.getTeamTable(team); }

    @Override
    public int getSpecCount(int team, User.Spec spec) { return repository.getSpecCount(team, spec);}

    @Override @Transactional
    public int getSeasPoints(int season, int team) { return repository.getSeasPoints(season, team);}

    @Override @Transactional
    public int getBestPlace(int team) { return repository.getBestPlace(team);}

    @Override @Transactional
    public int bestRacer(int team) { return repository.bestRacer(team);}

}