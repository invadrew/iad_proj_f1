package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User entity);

    void delete(User entity);

    User findById(int id);
}