package com.rogo.inv.iadprojf1.service.impl.cup;
import com.rogo.inv.iadprojf1.entity.cup.ConstrCupResult;
import com.rogo.inv.iadprojf1.repository.cupRepository.ConstrCupResultRepository;
import com.rogo.inv.iadprojf1.service.ConstrCupResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("constrCupService")
public class ConstrCupResultServiceImpl implements ConstrCupResultService {
    @Autowired
    ConstrCupResultRepository repository;

    @Override
    public List<ConstrCupResult> findAll() {
        return repository.findAll();
    }

    @Override
    public ConstrCupResult save(ConstrCupResult entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ConstrCupResult entity) {
        repository.delete(entity);
    }

    @Override
    public ConstrCupResult findById(int team, int season) {
        return repository.findById(team, season);
    }

    @Override @Transactional
    public List<Object[]> getConstrCupResultTable(int season) { return repository.getConstrCupResultTable(season);}
}
