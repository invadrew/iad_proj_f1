package com.rogo.inv.iadprojf1.entity.cup;

import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data @NoArgsConstructor
public class WorldCupResultPK implements Serializable {
    private Season season;

    private User racer;

    /* ================================
     constructors
    ================================ */
    public WorldCupResultPK(@NotNull Season season, @NotNull User racer) {
        this.season = season;
        this.racer = racer;
    }
}
