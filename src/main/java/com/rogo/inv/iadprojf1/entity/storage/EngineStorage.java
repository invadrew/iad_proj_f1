package com.rogo.inv.iadprojf1.entity.storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.DBUtility;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "engine_storage")
@Getter @Setter
@ToString @EqualsAndHashCode @NoArgsConstructor //(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EngineStorage extends BaseStorage {
    @NotNull @Column(length = DBUtility.MODEL_LEN)
    protected String model;

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
    public EngineStorage(@NotNull Team team, @NotNull ComponentCondition condition, @NotNull @Min(0) double price, @NotNull AcceptStatus status, @NotNull String model, @NotNull @Min(1) int cyclinders, @NotNull @Min(1) float capacity, @NotNull @Min(1) float mass, @NotNull @Min(1) float stroke) {
        super(team, condition, price, status);
        this.model = model;
        this.cyclinders = cyclinders;
        this.capacity = capacity;
        this.mass = mass;
        this.stroke = stroke;
    }
}
