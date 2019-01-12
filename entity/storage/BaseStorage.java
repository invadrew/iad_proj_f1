package com.rogo.inv.iadprojf1.entity.storage;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@MappedSuperclass
@NoArgsConstructor
public abstract class BaseStorage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    Team team;

    @NotNull @Column(length = 30)
    String model;

    @NotNull
    @Enumerated(EnumType.STRING)
    ComponentCondition condition;

    @NotNull @Min(1)
    long price;

    @NotNull
    @Enumerated(EnumType.STRING)
    AcceptStatus status;

    /* ================================
     constructors
    ================================ */
    BaseStorage(@NotNull Team team, @NotNull String model, @NotNull ComponentCondition condition, @NotNull @Min(1) long price, @NotNull AcceptStatus status) {
        this.team = team;
        this.model = model;
        this.condition = condition;
        this.price = price;
        this.status = status;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, team, model, condition, price, status);
    }

}
