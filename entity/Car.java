package com.rogo.inv.iadprojf1.entity;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;
import com.rogo.inv.iadprojf1.entity.race.RaceResult;
import com.rogo.inv.iadprojf1.entity.storage.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@Getter @Setter @NoArgsConstructor
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull @Column(length = 25)
    private String label;

    @NotNull @Column(length = 25)
    private String model;

    @NotNull @Min(1900)
    private int creationYear;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Team team;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Photo photo;

    @NotNull
    private boolean isReady;

    @NotNull
    @Column(unique = true)
    private CarcaseStorage currentCarcase;

    @NotNull
    @Column(unique = true)
    private EngineStorage currentEngine;

    @NotNull
    @Column(unique = true)
    private ChassisStorage currentChassis;

    @NotNull
    @Column(unique = true)
    private ElectronicsStorage currentElectronics;

    /* ================================
     values fetched from 'OneToMany' tables
    ================================ */
    @ManyToMany(mappedBy = "team_members")
    private List<TeamMember> racers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "component_changes")
    private List<ComponentChange> componentChanges = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_service")
    private List<PitStopService> pitStopServices = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_service")
    private List<PilotChange> pilotChanges = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race_result")
    private List<RaceResult> raceResults = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_repair")
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

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // label, model, team, currentCarcase, currentEngine, currentChassis, currentElectronics
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equal(label, car.label) &&
                Objects.equal(model, car.model) &&
                Objects.equal(team, car.team) &&
                Objects.equal(currentCarcase, car.currentCarcase) &&
                Objects.equal(currentEngine, car.currentEngine) &&
                Objects.equal(currentChassis, car.currentChassis) &&
                Objects.equal(currentElectronics, car.currentElectronics);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(label, model, team, currentCarcase, currentEngine, currentChassis, currentElectronics);
    }
}
