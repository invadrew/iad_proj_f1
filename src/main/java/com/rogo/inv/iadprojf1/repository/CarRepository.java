package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findById(int id);

    List<Car> findAllByTeam(Team team);
}
