package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.repository.UserRepository;
import com.rogo.inv.iadprojf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String login)  throws UsernameNotFoundException {

        User user = findByLogin(login);
        User.Spec role = user.getSpec();
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.name()));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
    }

}