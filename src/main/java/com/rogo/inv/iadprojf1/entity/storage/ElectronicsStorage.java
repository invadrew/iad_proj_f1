package com.rogo.inv.iadprojf1.entity.storage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
import com.rogo.inv.iadprojf1.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "electronics_storage")
@Getter @Setter
@ToString @EqualsAndHashCode @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ElectronicsStorage extends BaseStorage {

    @NotNull @Column(length = 30)
    private String telemetry;

    @NotNull @Column(length = 30)
    private String controlSystem;

    /* ================================
     constructors
    ================================ */
    public ElectronicsStorage(@NotNull Team team, @NotNull ComponentCondition condition, @NotNull @Min(0) double price, @NotNull AcceptStatus status, @NotNull String telemetry, @NotNull String controlSystem) {
        super(team, condition, price, status);
        this.telemetry = telemetry;
        this.controlSystem = controlSystem;
    }

    public ElectronicsStorage(@NotNull Team team, @NotNull ComponentCondition condition, @NotNull @Min(0) double price, @NotNull AcceptStatus status, User sender, @NotNull String telemetry, @NotNull String controlSystem) {
        super(team, condition, price, status, sender);
        this.telemetry = telemetry;
        this.controlSystem = controlSystem;
    }
}
