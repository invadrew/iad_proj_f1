package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.repository.UserRepository;
import com.rogo.inv.iadprojf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(User entity) {
        repository.delete(entity);
    }

    @Override
    public User findById(int id) {
        return repository.findById(id);
    }

    @Override
    public User findByLogin(String login) { return repository.findByLogin(login); }
}