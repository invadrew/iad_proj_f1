package com.rogo.inv.iadprojf1.entity.cup;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "constr_cup_result")
@Getter @Setter @NoArgsConstructor
public class ConstrCupResult {
    @EmbeddedId
    private ConstrCupResultPK pk;

    @NotNull @Min(0)
    private int place;

    @NotNull @Min(0)
    private int points;

    /* ================================
     constructors
    ================================ */
    public ConstrCupResult(@NotNull ConstrCupResultPK pk, @NotNull @Min(0) int place, @NotNull @Min(0) int points) {
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
        ConstrCupResult that = (ConstrCupResult) o;
        return Objects.equal(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pk);
    }
}


