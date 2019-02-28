package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.storage.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "component_changes")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ComponentChange {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_carcase_old")
    private CarcaseStorage currentCarcaseOld;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_carcase_new")
    private CarcaseStorage currentCarcaseNew;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_chassis_old")
    private ChassisStorage currentChassisOld;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_chassis_new")
    private ChassisStorage currentChassisNew;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_engine_old")
    private EngineStorage currentEngineOld;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_engine_new")
    private EngineStorage currentEngineNew;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_electronics_old")
    private ElectronicsStorage currentElectronicsOld;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_electronics_new")
    private ElectronicsStorage currentElectronicsNew;

    private String reason;

    private Date date;

    /* ================================
     constructors
    ================================ */
    public ComponentChange(@NotNull Car car, Race race, CarcaseStorage currentCarcaseOld, CarcaseStorage currentCarcaseNew, ChassisStorage currentChassisOld, ChassisStorage currentChassisNew, EngineStorage currentEngineOld, EngineStorage currentEngineNew, ElectronicsStorage currentElectronicsOld, ElectronicsStorage currentElectronicsNew, String reason, Date date) {
        this.car = car;
        this.race = race;
        this.currentCarcaseOld = currentCarcaseOld;
        this.currentCarcaseNew = currentCarcaseNew;
        this.currentChassisOld = currentChassisOld;
        this.currentChassisNew = currentChassisNew;
        this.currentEngineOld = currentEngineOld;
        this.currentEngineNew = currentEngineNew;
        this.currentElectronicsOld = currentElectronicsOld;
        this.currentElectronicsNew = currentElectronicsNew;
        this.reason = reason;
        this.date = date;
    }
}
