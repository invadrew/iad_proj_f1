package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car save(Car entity);

    void delete(Car entity);

    Car findById(int id);

    List<Car> findAllByTeam(Team team);

    List<Object[]> getConditionTable(int car);

    int updCarcase(@Param("carc") CarcaseStorage carc, @Param("car") Integer car);

    int updChassis(@Param("chass") ChassisStorage chass, @Param("car") Integer car);

    int updEngine(@Param("eng") EngineStorage eng, @Param("car") Integer car);

    int updElectronics(@Param("elec") ElectronicsStorage elec, @Param("car") Integer car);
}