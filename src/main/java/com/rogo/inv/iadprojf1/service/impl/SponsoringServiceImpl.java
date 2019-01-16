package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Sponsoring;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.repository.SponsoringRepository;
import com.rogo.inv.iadprojf1.service.SponsoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("sponsoringService")
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

    @Override @Transactional
    public int getTeamCount(int sponsor) { return repository.getTeamCount(sponsor);}

    @Override @Transactional
    public int getSumMoney(int sponsor) { return repository.getSumMoney(sponsor);}

    @Override @Transactional
    public List<Object[]> getSponsInfo(int team) { return repository.getSponsInfo(team);}

    @Override @Transactional
    public List<Sponsoring> findAllByTeamAndSponsor(Team team, Sponsor sponsor) { return repository.findAllByTeamAndSponsor(team, sponsor);}
}