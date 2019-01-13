package com.rogo.inv.iadprojf1.entity.race;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Piloting;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "race_results")
@IdClass(RaceResultPK.class)
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RaceResult {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private Race race;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Piloting piloting;

    @NotNull @Min(1)
    private int place;

    @Min(1)
    private int laps;

    @NotNull @Min(1)
    private int points;

    @NotNull @Min(1)
    private LocalTime raceTime;

    /* ================================
     constructors
    ================================ */
    public RaceResult(@NotNull Car car, @NotNull Race race, @NotNull Piloting piloting, @NotNull @Min(1) int place, @Min(1) int laps, @NotNull @Min(1) int points, @NotNull @Min(1) LocalTime raceTime) {
        this.car = car;
        this.race = race;
        this.piloting = piloting;
        this.place = place;
        this.laps = laps;
        this.points = points;
        this.raceTime = raceTime;
    }
}
