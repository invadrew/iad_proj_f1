package com.rogo.inv.iadprojf1.entity.cup;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data @NoArgsConstructor
public class ConstrCupResultPK implements Serializable {
    private Season season;

    private Team team;

    /* ================================
     constructors
    ================================ */
    public ConstrCupResultPK(@NotNull Season season, @NotNull Team racer) {
        this.season = season;
        this.team = racer;
    }

}
