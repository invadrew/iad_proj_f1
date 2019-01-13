package com.rogo.inv.iadprojf1.entity.race;

import com.rogo.inv.iadprojf1.entity.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data @NoArgsConstructor
public class RaceResultPK implements Serializable {
    private Car car;

    private Race race;

    /* ================================
     constructors
    ================================ */
    public RaceResultPK(@NotNull Car car, @NotNull Race race) {
        this.car = car;
        this.race = race;
    }
}
