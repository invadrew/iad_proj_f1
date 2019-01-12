package com.rogo.inv.iadprojf1.entity.pitstop;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@NoArgsConstructor
public abstract class BasePitStop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    Race race;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    PitStopPlace place;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    Car car;

    @NotNull
    @Enumerated(EnumType.STRING)
    AcceptStatus status;

    /* ================================
     constructors
    ================================ */
    public BasePitStop(@NotNull Race race, @NotNull PitStopPlace place, @NotNull Car car, @NotNull AcceptStatus status) {
        this.race = race;
        this.place = place;
        this.car = car;
        this.status = status;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, race, place, car);
    }
}
