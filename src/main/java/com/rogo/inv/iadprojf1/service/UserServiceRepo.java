package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceRepo implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }


}
