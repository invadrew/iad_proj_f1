package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> findAll();

    Photo save(Photo entity);

    void delete(Photo entity);

    Photo findById(int id);
}
