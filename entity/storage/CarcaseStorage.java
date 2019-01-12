package com.rogo.inv.iadprojf1.entity.storage;

import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "engine_storage")
@Getter @Setter @NoArgsConstructor
public class CarcaseStorage extends BaseStorage {
    @NotNull @Column(length = 25)
    private String material;

    @NotNull @Column(length = 25)
    private String rearWing;

    @NotNull @Column(length = 25)
    private String safetyArcs;

    @NotNull @Column(length = 40)
    private String wings;

    /* ================================
     constructors
    ================================ */
    public CarcaseStorage(@NotNull Team team, @NotNull String model, @NotNull ComponentCondition condition, @NotNull @Min(0) long price, @NotNull AcceptStatus status, @NotNull String material, @NotNull String rearWing, @NotNull String safetyArcs, @NotNull String wings) {
        super(team, model, condition, price, status);
        this.material = material;
        this.rearWing = rearWing;
        this.safetyArcs = safetyArcs;
        this.wings = wings;
    }

    /* ================================
     overridden Object methods
    ================================ */

    // id, team, model, material, rearWing, safetyArcs, wings, condition, status
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarcaseStorage that = (CarcaseStorage) o;
        return id == that.id &&
                Objects.equal(material, that.material) &&
                Objects.equal(rearWing, that.rearWing) &&
                Objects.equal(safetyArcs, that.safetyArcs) &&
                Objects.equal(wings, that.wings) &&
                Objects.equal(team, that.team) &&
                Objects.equal(model, that.model) &&
                condition == that.condition &&
                status == that.status;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), material, rearWing, safetyArcs, wings);
    }
}
