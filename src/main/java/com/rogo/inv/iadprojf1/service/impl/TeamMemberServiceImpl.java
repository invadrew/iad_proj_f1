package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.repository.TeamMemberRepository;
import com.rogo.inv.iadprojf1.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamMemberService")
public class TeamMemberServiceImpl implements TeamMemberService {
    @Autowired
    TeamMemberRepository repository;

    @Override
    public List<TeamMember> findAll() {
        return repository.findAll();
    }

    @Override
    public TeamMember save(TeamMember entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(TeamMember entity) {
        repository.delete(entity);
    }

    @Override
    public TeamMember findByUserId(int id) {
        return repository.findByUserId(id);
    }
}