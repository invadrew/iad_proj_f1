package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Sponsoring;
import com.rogo.inv.iadprojf1.repository.SponsoringRepository;
import com.rogo.inv.iadprojf1.service.SponsoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class SponsoringServiceImpl implements SponsoringService {
    @Autowired
    SponsoringRepository repository;

    @Override
    public List<Sponsoring> findAll() {
        return repository.findAll();
    }

    @Override
    public Sponsoring save(Sponsoring entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Sponsoring entity) {
        repository.delete(entity);
    }

    @Override
    public Sponsoring findById(int id) {
        return repository.findById(id);
    }
}