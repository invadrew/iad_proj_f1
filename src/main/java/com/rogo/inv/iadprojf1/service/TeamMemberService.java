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

    Integer pointsCount(int user, int season);

    Integer racingsCount(int user, int season);

    Integer allRaceCount(int user);

    Integer cupsWon (int user);

    Integer champCount (int user);

    Integer avergePlaceAtAll (int user);

    Integer avergePlaceAtSeason (int user, int season);

    Integer bestPlace (int user, int season);

    List<Object[]> getBestTrackTime(int user);

    List<Object[]> getAllRacers(int team);

    List<Object[]> getAllConstrAndMech(int team);
}
