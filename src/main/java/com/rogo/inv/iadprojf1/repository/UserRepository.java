package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);

    User findByLogin(String login);
}
