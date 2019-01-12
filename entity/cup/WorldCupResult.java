package com.rogo.inv.iadprojf1.entity.cup;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "world_cup_result")
@Getter @Setter @NoArgsConstructor
public class WorldCupResult {
    @NotNull
    @EmbeddedId
    private WorldCupResultPK pk;

    @NotNull @Min(0)
    private int place;

    @NotNull @Min(0)
    private int points;

    /* ================================
     constructors
    ================================ */
    public WorldCupResult(@NotNull WorldCupResultPK pk, @NotNull @Min(0) int place, @NotNull @Min(0) int points) {
        this.pk = pk;
        this.place = place;
        this.points = points;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // pk
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorldCupResult that = (WorldCupResult) o;
        return Objects.equal(pk, that.pk);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(pk);
    }
}


