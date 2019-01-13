package com.rogo.inv.iadprojf1.entity.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pit_stop_service")
@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PitStopService extends BasePitStop {
    @NotNull @Min(0)
    private int laps;

    @NotNull @Min(0)
    private float fuel;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TireTypes tires;

    /* ================================
     constructors
    ================================ */
    public PitStopService(@NotNull Race race, @NotNull PitStopPlace place, @NotNull Car car, @NotNull @Min(1) int laps, @NotNull @Min(1) int fuel, @NotNull TireTypes tires, @NotNull AcceptStatus status) {
        super(race, place, car, status);
        this.laps = laps;
        this.fuel = fuel;
        this.tires = tires;
    }

    /* ================================
     enums
    ================================ */
    public enum TireTypes {
        TOUGH,
        SOFT
    }
}
