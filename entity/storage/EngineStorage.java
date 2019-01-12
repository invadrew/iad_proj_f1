package com.rogo.inv.iadprojf1.entity.storage;

import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "engine_storage")
@Getter @Setter @NoArgsConstructor
public class EngineStorage extends BaseStorage {
    @NotNull @Min(1)
    private int cyclinders;

    @NotNull @Min(1)
    private float capacity;

    @NotNull @Min(1)
    private float mass;

    @NotNull @Min(1)
    private float stroke;

    /* ================================
     constructors
    ================================ */
    public EngineStorage(@NotNull Team team, @NotNull String model, @NotNull @Min(1) int cyclinders, @NotNull @Min(1) float capacity, @NotNull @Min(1) float mass, @NotNull @Min(1) float stroke, @NotNull ComponentCondition condition, @NotNull @Min(1) long price, @NotNull AcceptStatus status) {
        super(team, model, condition, price, status);
        this.cyclinders = cyclinders;
        this.capacity = capacity;
        this.mass = mass;
        this.stroke = stroke;
    }

    /* ================================
     overridden Object methods
    ================================ */

    // id, team, model, cyclinders, capacity, mass, stroke, condition, status
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineStorage that = (EngineStorage) o;
        return id == that.id &&
                cyclinders == that.cyclinders &&
                Float.compare(that.capacity, capacity) == 0 &&
                Float.compare(that.mass, mass) == 0 &&
                Float.compare(that.stroke, stroke) == 0 &&
                Objects.equal(team, that.team) &&
                Objects.equal(model, that.model) &&
                condition == that.condition &&
                status == that.status;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mass, stroke, condition, status);
    }
}
