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

    Integer getRacersCount(int team);

    Integer getMechanicsCount(int team);

    Integer getConstrsCount(int team);

    Integer getManagersCount(int team);

    Integer getSeasPoints(int season, int team);

    Integer getBestPlace(int team);

    List<Object[]> bestRacer(int team);

}