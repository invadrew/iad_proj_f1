package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;
import com.rogo.inv.iadprojf1.entity.race.RaceResult;
import com.rogo.inv.iadprojf1.entity.storage.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull @Column(length = 25)
    private String label;

    @NotNull @Column(length = 25)
    private String model;

    @NotNull @Min(1900)
    private int creationYear;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @NotNull
    private boolean isReady;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_carcase_id", unique = true)
    private CarcaseStorage currentCarcase;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_engine_id", unique = true)
    private EngineStorage currentEngine;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_chassis_id", unique = true)
    private ChassisStorage currentChassis;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_electronics_id", unique = true)
    private ElectronicsStorage currentElectronics;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "cars")
    @JsonBackReference
    private List<TeamMember> racers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private List<ComponentChange> componentChanges = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private List<PitStopService> pitStopServices = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private List<PilotChange> pilotChanges = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private List<RaceResult> raceResults = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private List<PitStopRepair> pitStopRepairs = new ArrayList<>();

    /* ================================
     constructors
    ================================ */
    public Car(@NotNull String label, @NotNull String model, @NotNull @Min(1900) int creationYear, @NotNull Team team, @NotNull Photo photo, @NotNull boolean isReady, @NotNull CarcaseStorage currentCarcase, @NotNull EngineStorage currentEngine, @NotNull ChassisStorage currentChassis, @NotNull ElectronicsStorage currentElectronics) {
        this.label = label;
        this.model = model;
        this.creationYear = creationYear;
        this.team = team;
        this.photo = photo;
        this.isReady = isReady;
        this.currentCarcase = currentCarcase;
        this.currentEngine = currentEngine;
        this.currentChassis = currentChassis;
        this.currentElectronics = currentElectronics;
    }
}
