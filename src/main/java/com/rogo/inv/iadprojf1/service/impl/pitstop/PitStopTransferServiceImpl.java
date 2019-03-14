package com.rogo.inv.iadprojf1.service.impl.pitstop;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopTransfer;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.repository.pitstopRepository.PitStopTransferRepository;
import com.rogo.inv.iadprojf1.service.PitStopTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pitstopTransferService")
public class PitStopTransferServiceImpl implements PitStopTransferService {
    @Autowired
    PitStopTransferRepository repository;

    @Override
    public List<PitStopTransfer> findAll() {
        return repository.findAll();
    }

    @Override
    public PitStopTransfer save(PitStopTransfer entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(PitStopTransfer entity) {
        repository.delete(entity);
    }

    @Override
    public PitStopTransfer findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<PitStopTransfer> findAllByStatusAndRace(AcceptStatus status, Race race) { return repository.findAllByStatusAndRace(status, race); }

    @Override
    public List<PitStopTransfer> findAllByTransferAndRace(PitStopTransfer.Transfers transfers, Race race) { return repository.findAllByTransferAndRace(transfers, race); }

    @Override
    public List<PitStopTransfer> findAllByTeamIdAndRace(Team team, Race race) { return repository.findAllByTeamIdAndRace(team, race); }
}