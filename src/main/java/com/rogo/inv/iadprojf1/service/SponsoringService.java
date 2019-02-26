package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import com.rogo.inv.iadprojf1.entity.Sponsoring;
import com.rogo.inv.iadprojf1.entity.Team;

import java.util.List;

public interface SponsoringService {
    List<Sponsoring> findAll();

    Sponsoring save(Sponsoring entity);

    void delete(Sponsoring entity);

    Sponsoring findById(int id);

    Integer getTeamCount(int sponsor);

    Integer getSumMoney(int sponsor);

    List<Object[]> getSponsInfo(int team);

    Integer getSumMoneyForTeam(int sponsor, int team);

    List<Sponsoring> findAllByTeamAndSponsor(Team team, Sponsor sponsor);

    Object[] getLatestSponsNews();
}