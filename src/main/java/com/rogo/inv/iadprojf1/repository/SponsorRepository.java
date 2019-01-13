package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

}
