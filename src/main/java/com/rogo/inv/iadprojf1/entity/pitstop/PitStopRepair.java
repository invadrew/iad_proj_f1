package com.rogo.inv.iadprojf1.entity.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.*;
import org.hibernate.type.ComponentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pit_stop_repair")
@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PitStopRepair extends BasePitStop {
    @NotNull
    private String comment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CarComponentType component;

    /* ================================
     constructors
    ================================ */
    public PitStopRepair(@NotNull Race race, @NotNull PitStopPlace place, @NotNull Car car, @NotNull AcceptStatus status, @NotNull String comment) {
        super(race, place, car, status);
        this.comment = comment;
    }
}
