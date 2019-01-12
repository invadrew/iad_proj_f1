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
@Table(name = "electronics_storage")
@Getter @Setter @NoArgsConstructor
public class ElectronicsStorage extends BaseStorage {
    @NotNull @Column(length = 30)
    private String telemetry;

    @NotNull @Column(length = 30)
    private String controlSystem;

    /* ================================
     constructors
    ================================ */
    public ElectronicsStorage(@NotNull Team team, @NotNull String model, @NotNull ComponentCondition condition, @NotNull @Min(0) long price, @NotNull AcceptStatus status, @NotNull String telemetry, @NotNull String controlSystem) {
        super(team, model, condition, price, status);
        this.telemetry = telemetry;
        this.controlSystem = controlSystem;
    }

    /* ================================
     overridden Object methods
    ================================ */

    // id, team, model, condition, price, status, telemetry, controlSystem
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectronicsStorage that = (ElectronicsStorage) o;
        return id == that.id &&
                price == that.price &&
                Objects.equal(team, that.team) &&
                Objects.equal(model, that.model) &&
                condition == that.condition &&
                status == that.status &&
                Objects.equal(telemetry, that.telemetry) &&
                Objects.equal(controlSystem, that.controlSystem)
                ;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), telemetry, controlSystem);
    }

}
