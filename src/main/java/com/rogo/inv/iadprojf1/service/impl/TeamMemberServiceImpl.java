package com.rogo.inv.iadprojf1.service.impl;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.repository.TeamMemberRepository;
import com.rogo.inv.iadprojf1.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public TeamMember findByUserId(int id) {return repository.findByUserId(id);}

    @Override @Transactional
    public List<TeamMember> getAllspecificType(int team, User.Spec spec) { return repository.getAllspecificType(team,spec); }

    @Override
    public List<TeamMember> findAllByTeam (Team team) { return repository.findAllByTeam(team); }

    @Override
    public int pointsCount(int user, int season) { return  repository.pointsCount(user, season);}

    @Override
    public int racingsCount(int user, int season) { return repository.racingsCount(user, season);}

    @Override
    public int allRaceCount(int user) { return repository.allRaceCount(user);}

    @Override
    public int cupsWon (int user) { return  repository.cupsWon(user);}

    @Override
    public int champCount (int user) { return repository.champCount(user);}

    @Override
    public int avergePlaceAtAll (int user) { return repository.avergePlaceAtAll(user);}

    @Override
    public int avergePlaceAtSeason (int user, int season) { return repository.avergePlaceAtSeason(user, season);}

    @Override
    public int bestPlace (int user, int season) { return repository.bestPlace(user,season);}

    @Override
    public List<Object[]> getBestTrackTime(int user) { return repository.getBestTrackTime(user);}
}