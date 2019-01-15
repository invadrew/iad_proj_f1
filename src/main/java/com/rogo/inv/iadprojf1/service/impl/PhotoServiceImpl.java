package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Photo;
import com.rogo.inv.iadprojf1.repository.PhotoRepository;
import com.rogo.inv.iadprojf1.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoRepository repository;

    @Override
    public List<Photo> findAll() {
        return repository.findAll();
    }

    @Override
    public Photo save(Photo entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Photo entity) {
        repository.delete(entity);
    }

    @Override
    public Photo findById(int id) {
        return repository.findById(id);
    }
}