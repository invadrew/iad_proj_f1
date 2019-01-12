package com.rogo.inv.iadprojf1.entity.pitstop;

import com.google.common.base.Objects;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.ComponentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pit_stop_repair")
@Getter @Setter @NoArgsConstructor
public class PitStopRepair extends BasePitStop {
    @NotNull
    private String comment;

    @NotNull
    private ComponentType component;

    /* ================================
     constructors
    ================================ */
    public PitStopRepair(@NotNull Race race, @NotNull PitStopPlace place, @NotNull Car car, @NotNull AcceptStatus status, @NotNull String comment) {
        super(race, place, car, status);
        this.comment = comment;
    }

    /* ================================
     overridden Object methods
    ================================ */
    // id, comment, component, race, place, car
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PitStopRepair that = (PitStopRepair) o;
        return id == that.id &&
                Objects.equal(comment, that.comment) &&
                Objects.equal(component, that.component) &&
                Objects.equal(race, that.race) &&
                Objects.equal(place, that.place) &&
                Objects.equal(car, that.car);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), id, comment, component);
    }
}
