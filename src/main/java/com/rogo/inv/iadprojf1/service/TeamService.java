package com.rogo.inv.iadprojf1.service;


import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;

import java.util.List;

public interface TeamService {
    List<Team> findAll();

    Team save(Team entity);

    void delete(Team entity);

    Team findById(int id);

    Team findByName(String name);

    List<Object[]> getTeamTable(int team);

    int getSpecCount(int team, User.Spec spec);

    int getSeasPoints(int season, int team);

    int getBestPlace(int team);

    int bestRacer(int team);

}