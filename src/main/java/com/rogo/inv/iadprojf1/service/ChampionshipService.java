package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.cup.Championship;

import java.util.List;

public interface ChampionshipService {
    List<Championship> findAll();

    Championship save(Championship entity);

    void delete(Championship entity);

    Championship findById(int id);

    //List<Championship> getAllChamps(int season);
    List<Championship> getAllBySeason(Season season);
}
