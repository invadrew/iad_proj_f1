package com.rogo.inv.iadprojf1.repository;

import com.rogo.inv.iadprojf1.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer> {
    TeamMember findByUserId(int id);
}