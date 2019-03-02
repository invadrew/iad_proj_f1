package com.rogo.inv.iadprojf1.repository.pitstopRepository;

import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitStopRepairRepository extends JpaRepository<PitStopRepair, Integer> {
    PitStopRepair findById(int id);

    List<PitStopRepair> findAllByCar(Car car);
}
