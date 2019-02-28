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

    // CarcaseStorage findCarcaseStorageById(int id);

   // ChassisStorage findChassisStorageById(int id);

 //   EngineStorage findEngineStorageById(int id);

    //ElectronicsStorage findElectronicsStorageById(int id);

    List<Object[]> getConditionTable(int car);

    int updCarcase(@Param("carc") CarcaseStorage carc, @Param("car") Integer car);
}