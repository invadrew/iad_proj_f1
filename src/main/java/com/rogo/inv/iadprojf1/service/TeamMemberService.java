package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.User;

import java.util.List;

public interface TeamMemberService {
    List<TeamMember> findAll();

    TeamMember save(TeamMember entity);

    void delete(TeamMember entity);

    TeamMember findByUserId(int id);

    List<TeamMember> getAllspecificType(int team, User.Spec spec);

    List<TeamMember> findAllByTeam (Team team);

    int pointsCount(int user, int season);

    int racingsCount(int user, int season);

    int allRaceCount(int user);

    int cupsWon (int user);

    int champCount (int user);

    int avergePlaceAtAll (int user);

    int avergePlaceAtSeason (int user, int season);

    int bestPlace (int user, int season);

    List<Object[]> getBestTrackTime(int user);
}
