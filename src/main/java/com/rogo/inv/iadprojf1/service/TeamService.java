package com.rogo.inv.iadprojf1.service;


import com.rogo.inv.iadprojf1.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> findAll();

    Team save(Team entity);

    void delete(Team entity);

    Team findById(int id);

    Team findByName(String name);

    List<Object[]> getTeamTable(int team);
}