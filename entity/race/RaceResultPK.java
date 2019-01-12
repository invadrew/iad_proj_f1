package com.rogo.inv.iadprojf1.entity.race;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class RaceResultPK {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Car car;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Race race;

    /* ================================
     constructors
    ================================ */
    public RaceResultPK(@NotNull Car car, @NotNull Race race) {
        this.car = car;
        this.race = race;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // car, race
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceResultPK that = (RaceResultPK) o;
        return Objects.equal(car, that.car) &&
                Objects.equal(race, that.race);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(car, race);
    }
}
