package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    Photo findById(int id);
}
