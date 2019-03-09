package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ElectronicsStorageService {
    List<ElectronicsStorage> findAll();

    ElectronicsStorage save(ElectronicsStorage entity);

    void delete(ElectronicsStorage entity);

    ElectronicsStorage findById(int id);

    List<ElectronicsStorage> findAllByTeam(Team team);

    int repairElectronics( @Param("electronics") Integer electronics);

    List<ElectronicsStorage> findAllByTeamAndStatus(Team team, AcceptStatus status);

    List<ElectronicsStorage> findAllBySender(User sender);

}
