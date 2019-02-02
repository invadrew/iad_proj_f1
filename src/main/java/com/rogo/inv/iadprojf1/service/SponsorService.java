package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Sponsor;

import java.util.List;

public interface SponsorService {
    List<Sponsor> findAll();

    Sponsor save(Sponsor entity);

    void delete(Sponsor entity);

    Sponsor findByUserId(int id);

    List<Object[]> getTeamSponsorsList(int team);
}
