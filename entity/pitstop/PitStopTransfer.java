package com.rogo.inv.iadprojf1.entity.pitstop;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.race.Race;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pit_stop_transfer")
@Getter @Setter @NoArgsConstructor
public class PitStopTransfer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Race race;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private PitStopPlace placeFrom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private PitStopPlace placeTo;

    @NotNull
    private Transfers transfer;

    @NotNull @Min(1)
    private float amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    AcceptStatus status;

    /* ================================
     constructors
    ================================ */
    public PitStopTransfer(@NotNull Race race, @NotNull PitStopPlace placeFrom, @NotNull PitStopPlace placeTo, @NotNull Transfers transfer, @NotNull @Min(1) float amount, @NotNull AcceptStatus status) {
        this.race = race;
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.transfer = transfer;
        this.amount = amount;
        this.status = status;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // id, race, placeFrom, placeTo, transfer, amount, status
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitStopTransfer that = (PitStopTransfer) o;
        return id == that.id &&
                Float.compare(that.amount, amount) == 0 &&
                Objects.equal(race, that.race) &&
                Objects.equal(placeFrom, that.placeFrom) &&
                Objects.equal(placeTo, that.placeTo) &&
                transfer == that.transfer &&
                status == that.status;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, race, placeFrom, placeTo, transfer, amount, status);
    }

    /* ================================
     enums
    ================================ */
    enum Transfers {
        TOUGH,
        FUEL,
        SOFT
    }
}
