package com.rogo.inv.iadprojf1.entity.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "pit_stop_service") @Data
@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PitStopService extends BasePitStop {
    @Min(0)
    private Integer laps;

    @NotNull @Min(0)
    private float fuel;

    @Enumerated(EnumType.STRING)
    private TireTypes tires;

    private String sender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team teamId;

    private LocalTime time;

    private String comment;


    /* ================================
     constructors
    ================================ */
    public PitStopService(@NotNull Race race, @NotNull PitStopPlace place, @NotNull Car car, Integer laps, @NotNull @Min(0) float fuel, TireTypes tires, @NotNull AcceptStatus status,
                          String comment, LocalTime time, Team team, String sender) {
        super(race, place, car, status);
        this.laps = laps;
        this.fuel = fuel;
        this.tires = tires;
        this.comment = comment;
        this.teamId = team;
        this.time = time;
        this.sender = sender;
    }

    /* ================================
     enums
    ================================ */
    public enum TireTypes {
        TOUGH,
        SOFT
    }
}
