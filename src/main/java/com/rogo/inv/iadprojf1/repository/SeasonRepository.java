package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
    Season findByYear(int id);

}
