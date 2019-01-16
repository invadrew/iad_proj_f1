package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.message.Chat;
import com.rogo.inv.iadprojf1.repository.CarRepository;
import com.rogo.inv.iadprojf1.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository repository;

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Car save(Car entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Car entity) {
        repository.delete(entity);
    }

    @Override
    public Car findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Car> findAllByTeam(Team team) { return repository.findAllByTeam(team); }
}