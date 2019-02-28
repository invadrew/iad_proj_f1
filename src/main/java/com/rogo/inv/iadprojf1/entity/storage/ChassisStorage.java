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
@Table(name = "chassis_storage")
@Getter @Setter
@ToString @EqualsAndHashCode @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ChassisStorage extends BaseStorage {
    @NotNull @Column(length = DBUtility.MODEL_LEN)
    protected String model;

    @NotNull @Min(1)
    private float height;

    @NotNull @Min(1)
    private float width;

    /* ================================
     constructors
    ================================ */
    public ChassisStorage(@NotNull Team team, @NotNull ComponentCondition condition, @NotNull @Min(0) long price, @NotNull AcceptStatus status, @NotNull String model, @NotNull @Min(1) float height, @NotNull @Min(1) float width) {
        super(team, condition, price, status);
        this.model = model;
        this.height = height;
        this.width = width;
    }
}
