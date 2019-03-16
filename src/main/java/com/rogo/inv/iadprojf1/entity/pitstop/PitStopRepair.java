package com.rogo.inv.iadprojf1.entity.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.*;
import org.hibernate.type.ComponentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "pit_stop_repair") @Data
@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PitStopRepair extends BasePitStop {
    @NotNull
    private String comment;

    private String sender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team teamId;

    private LocalTime time;
    /* ================================
     constructors
    ================================ */
    public PitStopRepair(@NotNull Race race, PitStopPlace place, @NotNull Car car, @NotNull AcceptStatus status, @NotNull String comment, String sender, Team teamId,
                         LocalTime time) {
        super(race, place, car, status);
        this.comment = comment;
        this.sender = sender;
        this.teamId = teamId;
        this.time = time;
    }
}
