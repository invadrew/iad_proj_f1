package com.rogo.inv.iadprojf1.entity.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.DBUtility;
import com.rogo.inv.iadprojf1.entity.Team;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pit_stop_places")
@ToString @EqualsAndHashCode @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PitStopPlace {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull @Column(length = DBUtility.NAME_LEN)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @NotNull @Min(0)
    private int tough;

    @NotNull @Min(0)
    private int soft;

    @NotNull @Min(0)
    private float fuel;

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
}
