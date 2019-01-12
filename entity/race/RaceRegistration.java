package com.rogo.inv.iadprojf1.entity.race;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "race_registration")
@Getter @Setter @NoArgsConstructor
public class RaceRegistration {
    @NotNull
    @EmbeddedId
    private RaceRegistrationPK pk;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private TeamMember firstPilot;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Car firstCar;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private TeamMember secondPilot;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Car secondCar;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AcceptStatus status;

    /* ================================
     constructors
    ================================ */
    public RaceRegistration(@NotNull RaceRegistrationPK pk, @NotNull TeamMember firstPilot, @NotNull Car firstCar, @NotNull TeamMember secondPilot, @NotNull Car secondCar, @NotNull AcceptStatus status) {
        this.pk = pk;
        this.firstPilot = firstPilot;
        this.firstCar = firstCar;
        this.secondPilot = secondPilot;
        this.secondCar = secondCar;
        this.status = status;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // pk, firstPilot, firstCar, secondPilot, secondCar, status
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceRegistration that = (RaceRegistration) o;
        return Objects.equal(pk, that.pk) &&
                Objects.equal(firstPilot, that.firstPilot) &&
                Objects.equal(firstCar, that.firstCar) &&
                Objects.equal(secondPilot, that.secondPilot) &&
                Objects.equal(secondCar, that.secondCar) &&
                status == that.status;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(pk, firstPilot, firstCar, secondPilot, secondCar, status);
    }
}
