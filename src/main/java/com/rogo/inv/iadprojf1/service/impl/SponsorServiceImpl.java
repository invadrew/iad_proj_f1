package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.repository.SponsorRepository;
import com.rogo.inv.iadprojf1.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sponsorService")
public class SponsorServiceImpl implements SponsorService {
    @Autowired
    SponsorRepository repository;

    @Override
    public List<Sponsor> findAll() {
        return repository.findAll();
    }

    @Override
    public Sponsor save(Sponsor entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Sponsor entity) {
        repository.delete(entity);
    }

    @Override
    public Sponsor findByUserId(int id) {
        return repository.findByUserId(id);
    }

    @Override
    public List<Object[]> getTeamSponsorsList(int team) {return repository.getTeamSponsorsList(team);}

    @Override
    public List<Object[]> getTeamsBySponsor(int sponsor) {return repository.getTeamsBySponsor(sponsor);}
}