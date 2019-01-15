package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Sponsoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsoringRepository extends JpaRepository<Sponsoring, Integer> {
    Sponsoring findById(int id);
}