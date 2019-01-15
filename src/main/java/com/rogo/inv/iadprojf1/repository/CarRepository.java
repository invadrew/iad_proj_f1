package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findById(int id);
}
