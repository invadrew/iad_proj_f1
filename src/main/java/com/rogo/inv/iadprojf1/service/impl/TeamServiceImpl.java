package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
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

    @Override
    public Team findByName(String name) { return repository.findByName(name); }

    @Override
    public List<Object[]> getTeamTable(int team) { return repository.getTeamTable(team); }

    @Override
    public Integer getRacersCount(int team) { return repository.getRacersCount(team);}

    @Override
    public Integer getMechanicsCount(int team) { return repository.getMechanicsCount(team);}

    @Override
    public Integer getConstrsCount(int team) { return repository.getConstrsCount(team);}

    @Override
    public Integer getManagersCount(int team) { return repository.getManagersCount(team);}

    @Override
    public Integer getSeasPoints(int season, int team) { return repository.getSeasPoints(season, team);}

    @Override
    public Integer getBestPlace(int team) { return repository.getBestPlace(team);}

    @Override
    public List<Object[]> bestRacer(int team) { return repository.bestRacer(team);}

}