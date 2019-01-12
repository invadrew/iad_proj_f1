package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sponsorService")
public class SponsorServiceRepo implements SponsorService {
    @Autowired
    SponsorRepository repository;

    @Override
    public List<Sponsor> findAll() {
        return repository.findAll();
    }


}
