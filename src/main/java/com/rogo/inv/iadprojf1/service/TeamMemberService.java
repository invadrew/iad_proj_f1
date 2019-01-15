package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.TeamMember;

import java.util.List;

public interface TeamMemberService {
    List<TeamMember> findAll();

    TeamMember save(TeamMember entity);

    void delete(TeamMember entity);

    TeamMember findByUserId(int id);
}
