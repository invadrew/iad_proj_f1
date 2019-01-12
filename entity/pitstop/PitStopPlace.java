package com.rogo.inv.iadprojf1.entity.pitstop;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pit_stop_places")
@Getter @Setter @NoArgsConstructor
public class PitStopPlace {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull @Column(length = 20)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Team team;

    @NotNull @Min(1)
    private int tough;

    @NotNull @Min(1)
    private int soft;

    @NotNull @Min(1)
    private int fuel;

    /* ================================
     constructors
    ================================ */
    public PitStopPlace(@NotNull String name, @NotNull Team team, @NotNull @Min(1) int tough, @NotNull @Min(1) int soft, @NotNull @Min(1) int fuel) {
        this.name = name;
        this.team = team;
        this.tough = tough;
        this.soft = soft;
        this.fuel = fuel;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // id, name, team
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitStopPlace that = (PitStopPlace) o;
        return id == that.id &&
                Objects.equal(name, that.name) &&
                Objects.equal(team, that.team);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, team);
    }
}
