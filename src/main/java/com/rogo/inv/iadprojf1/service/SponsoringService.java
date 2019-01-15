package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Sponsoring;

import java.util.List;

public interface SponsoringService {
    List<Sponsoring> findAll();

    Sponsoring save(Sponsoring entity);

    void delete(Sponsoring entity);

    Sponsoring findById(int id);
}