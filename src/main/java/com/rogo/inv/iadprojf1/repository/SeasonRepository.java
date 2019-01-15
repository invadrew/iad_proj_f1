package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
    Season findByYear(int id);
}
