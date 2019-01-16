package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car save(Car entity);

    void delete(Car entity);

    Car findById(int id);

    List<Car> findAllByTeam(Team team);
}