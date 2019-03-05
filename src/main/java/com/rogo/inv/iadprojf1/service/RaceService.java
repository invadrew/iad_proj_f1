package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.race.Race;

import java.util.List;

public interface RaceService {
    List<Race> findAll();

    Race save(Race entity);

    void delete(Race entity);

    Race findById(int id);

    List<Object[]> getCurrentEvent();

    Race findTopByOrderByDateTimeDesc();

    Integer getStageNum(int year);
}