package com.rogo.inv.iadprojf1.entity.storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "carcase_storage")
@Getter @Setter
@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    public CarcaseStorage(@NotNull Team team, @NotNull ComponentCondition condition, @NotNull @Min(0) long price, @NotNull AcceptStatus status, @NotNull String material, @NotNull String rearWing, @NotNull String safetyArcs, @NotNull String wings) {
        super(team, condition, price, status);
        this.material = material;
        this.rearWing = rearWing;
        this.safetyArcs = safetyArcs;
        this.wings = wings;
    }
}
