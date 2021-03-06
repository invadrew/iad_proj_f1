package com.rogo.inv.iadprojf1.entity.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "pit_stop_transfer")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PitStopTransfer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private Race race;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_from_id")
    private PitStopPlace placeFrom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_to_id")
    private PitStopPlace placeTo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Transfers transfer;

    @NotNull @Min(1)
    private float amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AcceptStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team teamId;

    private LocalTime time;

    /* ================================
     constructors
    ================================ */
    public PitStopTransfer(@NotNull Race race, @NotNull PitStopPlace placeFrom, @NotNull PitStopPlace placeTo, @NotNull Transfers transfer, @NotNull @Min(1) float amount, @NotNull AcceptStatus status, Team team
    , LocalTime time) {
        this.race = race;
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.transfer = transfer;
        this.amount = amount;
        this.status = status;
        this.teamId = team;
        this.time = time;
    }

    /* ================================
     enums
    ================================ */
    public enum Transfers {
        TOUGH,
        FUEL,
        SOFT
    }
}
