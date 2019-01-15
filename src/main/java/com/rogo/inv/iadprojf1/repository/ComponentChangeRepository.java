package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.ComponentChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentChangeRepository extends JpaRepository<ComponentChange, Integer> {
    ComponentChange findById(int id);
}
