package com.rogo.inv.iadprojf1.entity.race;

import com.rogo.inv.iadprojf1.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data @NoArgsConstructor
public class RaceRegistrationPK implements Serializable {
    private Team team;

    private Race race;

    /* ================================
     constructors
    ================================ */
    public RaceRegistrationPK(@NotNull Team team, @NotNull Race race) {
        this.team = team;
        this.race = race;
    }
}
