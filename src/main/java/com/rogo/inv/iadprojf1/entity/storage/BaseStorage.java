package com.rogo.inv.iadprojf1.entity.storage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@MappedSuperclass
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseStorage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    protected Team team;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected ComponentCondition condition;

    @NotNull @Min(0)
    protected long price;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected AcceptStatus status;

    /* ================================
     constructors
    ================================ */
    public BaseStorage(@NotNull Team team, @NotNull ComponentCondition condition, @NotNull @Min(0) long price, @NotNull AcceptStatus status) {
        this.team = team;
        this.condition = condition;
        this.price = price;
        this.status = status;
    }
}
