package com.rogo.inv.iadprojf1.service.impl.storage;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.repository.storageRepository.CarcaseStorageRepository;
import com.rogo.inv.iadprojf1.service.CarcaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("carcaseService")
public class CarcaseStorageServiceImpl implements CarcaseStorageService {
    @Autowired
    CarcaseStorageRepository repository;

    @Override
    public List<CarcaseStorage> findAll() {
        return repository.findAll();
    }

    @Override
    public CarcaseStorage save(CarcaseStorage entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(CarcaseStorage entity) {
        repository.delete(entity);
    }

    @Override
    public CarcaseStorage findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<CarcaseStorage> findAllByTeam(Team team) { return repository.findAllByTeam(team);}

    @Override @Transactional
    public int repairCarcase( @Param("carcase") Integer carcase) { return  repository.repairCarcase(carcase); }

    @Override
    public List<CarcaseStorage> findAllByTeamAndStatus(Team team, AcceptStatus status) { return repository.findAllByTeamAndStatus(team, status); }

    @Override
    public List<CarcaseStorage> findAllBySender(User sender) { return repository.findAllBySender(sender); }
}
