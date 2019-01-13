package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamService")
public class TeamServiceRepo implements TeamService {
    @Autowired
    TeamRepository repository;

    @Override
    public List<Team> findAll() {
        return repository.findAll();
    }


}
