package com.rogo.inv.iadprojf1.entity.pitstop;

import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pilot_change")
@Getter @Setter @NoArgsConstructor
public class PilotChange extends BasePitStop {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
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

    /* ================================
     overridden Object methods
    ================================ */

    // pilot, comment, race, place, car
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PilotChange that = (PilotChange) o;
        return id == that.id &&
                Objects.equal(pilot, that.pilot) &&
                Objects.equal(comment, that.comment) &&
                Objects.equal(race, that.race) &&
                Objects.equal(place, that.place) &&
                Objects.equal(car, that.car);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), pilot, comment);
    }
}
