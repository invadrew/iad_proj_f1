package com.rogo.inv.iadprojf1.entity.pitstop;

import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pit_stop_service")
@Getter @Setter @NoArgsConstructor
public class PitStopService extends BasePitStop {
    @NotNull @Min(1)
    private int laps;

    @NotNull @Min(1)
    private int fuel;

    @NotNull
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
     overridden Object methods
    ================================ */
    // id, race, place, car, laps, fuel, tires, status
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitStopService that = (PitStopService) o;
        return id == that.id &&
                laps == that.laps &&
                fuel == that.fuel &&
                tires == that.tires &&
                Objects.equal(race, that.race) &&
                Objects.equal(place, that.place) &&
                Objects.equal(car, that.car) &&
                status == that.status;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), id, laps, fuel, tires, status);
    }

    /* ================================
     enums
    ================================ */
    public enum TireTypes {
        TOUGH,
        SOFT
    }
}
