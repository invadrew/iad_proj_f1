package com.rogo.inv.iadprojf1.entity.race;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Piloting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "race_result")
@Getter @Setter @NoArgsConstructor
public class RaceResult {
    @NotNull
    @EmbeddedId
    private RaceResultPK pk;

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
    public RaceResult(@NotNull RaceResultPK pk, @NotNull Piloting piloting, @NotNull @Min(1) int place, @Min(1) int laps, @NotNull @Min(1) int points, @NotNull @Min(1) LocalTime raceTime) {
        this.pk = pk;
        this.piloting = piloting;
        this.place = place;
        this.laps = laps;
        this.points = points;
        this.raceTime = raceTime;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // pk, piloting, place, laps, points, raceTime
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceResult that = (RaceResult) o;
        return place == that.place &&
                laps == that.laps &&
                points == that.points &&
                Objects.equal(pk, that.pk) &&
                Objects.equal(piloting, that.piloting) &&
                Objects.equal(raceTime, that.raceTime);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(pk, piloting, place, laps, points, raceTime);
    }
}
