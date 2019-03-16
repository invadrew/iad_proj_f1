package com.rogo.inv.iadprojf1.entity.pitstop;

import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Data @NoArgsConstructor
public abstract class BasePitStop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    Race race;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    PitStopPlace place;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    Car car;

    @NotNull
    @Enumerated(EnumType.STRING)
    AcceptStatus status;

    /* ================================
     constructors
    ================================ */
    public BasePitStop(@NotNull Race race, PitStopPlace place, @NotNull Car car, @NotNull AcceptStatus status) {
        this.race = race;
        this.place = place;
        this.car = car;
        this.status = status;
    }
}
