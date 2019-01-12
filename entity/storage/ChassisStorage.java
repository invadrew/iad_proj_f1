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
public class ChassisStorage extends BaseStorage {
    @NotNull @Min(1)
    private float height;

    @NotNull @Min(1)
    private float width;

    /* ================================
     constructors
    ================================ */
    public ChassisStorage(@NotNull Team team, @NotNull String model, @NotNull ComponentCondition condition, @NotNull @Min(0) long price, @NotNull AcceptStatus status, @NotNull @Min(0) float height, @NotNull @Min(0) float width) {
        super(team, model, condition, price, status);
        this.height = height;
        this.width = width;
    }

    /* ================================
     overridden Object methods
    ================================ */

    // id, team, model, condition, price, status, height, width
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChassisStorage that = (ChassisStorage) o;
        return id == that.id &&
                price == that.price &&
                Objects.equal(team, that.team) &&
                Objects.equal(model, that.model) &&
                condition == that.condition &&
                status == that.status &&
                Float.compare(that.height, height) == 0 &&
                Float.compare(that.width, width) == 0;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), height, width);
    }
}
