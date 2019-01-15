package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findById(int id);
}
