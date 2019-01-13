package com.rogo.inv.iadprojf1.entity.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pilot_change")
@Getter @Setter
@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PilotChange extends BasePitStop {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilot_id")
    private TeamMember pilot;

    @NotNull
    private String comment;

    /* ================================
     constructors
    ================================ */
    public PilotChange(@NotNull Race race, @NotNull PitStopPlace place, @NotNull Car car, @NotNull AcceptStatus status, @NotNull TeamMember pilot, @NotNull String comment) {
        super(race, place, car, status);
        this.pilot = pilot;
        this.comment = comment;
    }
}
