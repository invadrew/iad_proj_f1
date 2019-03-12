package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;
import com.rogo.inv.iadprojf1.entity.race.RaceResult;
import com.rogo.inv.iadprojf1.entity.storage.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @Column(name = "id")
    private int id;

    @NotNull @Column(length = 25)
    private String label;

    @NotNull @Column(length = 25)
    private String model;

    @Min(1900)
    private int creationYear;

    @ToString.Exclude
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @NotNull
    private Boolean isReady;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_carcase_id", unique = true)
    private CarcaseStorage currentCarcase;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_engine_id", unique = true)
    private EngineStorage currentEngine;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_chassis_id", unique = true)
    private ChassisStorage currentChassis;

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

    @Enumerated(EnumType.STRING)
    protected AcceptStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender")
    private User sender;

    @Column(name = "comment")
    private String comment;

    @Column(name = "if_dismantled")
    private Boolean ifDismantled = false;

    /* ================================
     constructors
    ================================ */
    public Car(@NotNull String label, @NotNull String model, @Min(1900) int creationYear, @NotNull Team team,  Photo photo, @NotNull Boolean isReady, @NotNull CarcaseStorage currentCarcase, @NotNull EngineStorage currentEngine, @NotNull ChassisStorage currentChassis, @NotNull ElectronicsStorage currentElectronics, AcceptStatus status) {
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
        this.status = status;
    }

    public Car(@NotNull String label, @NotNull String model, @Min(1900) int creationYear, @NotNull Team team,  Photo photo, @NotNull Boolean isReady, @NotNull CarcaseStorage currentCarcase, @NotNull EngineStorage currentEngine, @NotNull ChassisStorage currentChassis, @NotNull ElectronicsStorage currentElectronics, AcceptStatus status, User sender) {
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
        this.status = status;
        this.sender = sender;
    }
}
