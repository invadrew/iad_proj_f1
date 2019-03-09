package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarcaseStorageService {
    List<CarcaseStorage> findAll();

    CarcaseStorage save(CarcaseStorage entity);

    void delete(CarcaseStorage entity);

    CarcaseStorage findById(int id);

    List<CarcaseStorage> findAllByTeam(Team team);

    int repairCarcase( @Param("carcase") Integer carcase);

    List<CarcaseStorage> findAllByTeamAndStatus(Team team, AcceptStatus status);

    List<CarcaseStorage> findAllBySender(User sender);
}