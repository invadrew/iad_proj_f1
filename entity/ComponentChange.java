package com.rogo.inv.iadprojf1.entity;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.storage.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "component_changes")
@Getter @Setter @NoArgsConstructor
public class ComponentChange {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private CarcaseStorage currentCarcaseOld;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private CarcaseStorage currentCarcaseNew;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private ChassisStorage currentChassisOld;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private ChassisStorage currentChassisNew;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private EngineStorage currentEngineOld;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private EngineStorage currentEngineNew;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private ElectronicsStorage currentElectronicsOld;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private ElectronicsStorage currentElectronicsNew;

    private String reason;

    private LocalDateTime date;

    /* ================================
     constructors
    ================================ */
    public ComponentChange(@NotNull Car car, Race race, CarcaseStorage currentCarcaseOld, CarcaseStorage currentCarcaseNew, ChassisStorage currentChassisOld, ChassisStorage currentChassisNew, EngineStorage currentEngineOld, EngineStorage currentEngineNew, ElectronicsStorage currentElectronicsOld, ElectronicsStorage currentElectronicsNew, String reason, LocalDateTime date) {
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

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // id, car, currentCarcaseOld, currentCarcaseNew, currentChassisOld, currentChassisNew, currentEngineOld, currentEngineNew, currentElectronicsOld, currentElectronicsNew, date
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentChange that = (ComponentChange) o;
        return id == that.id &&
                Objects.equal(car, that.car) &&
                Objects.equal(currentCarcaseOld, that.currentCarcaseOld) &&
                Objects.equal(currentCarcaseNew, that.currentCarcaseNew) &&
                Objects.equal(currentChassisOld, that.currentChassisOld) &&
                Objects.equal(currentChassisNew, that.currentChassisNew) &&
                Objects.equal(currentEngineOld, that.currentEngineOld) &&
                Objects.equal(currentEngineNew, that.currentEngineNew) &&
                Objects.equal(currentElectronicsOld, that.currentElectronicsOld) &&
                Objects.equal(currentElectronicsNew, that.currentElectronicsNew) &&
                Objects.equal(date, that.date);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, car, currentCarcaseOld, currentCarcaseNew, currentChassisOld, currentChassisNew, currentEngineOld, currentEngineNew, currentElectronicsOld, currentElectronicsNew, date);
    }
}
