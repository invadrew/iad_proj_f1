package com.rogo.inv.iadprojf1.entity.race;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.Car;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.TeamMember;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "race_registration")
@IdClass(RaceRegistrationPK.class)
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RaceRegistration {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private Race race;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_pilot")
    private TeamMember firstPilot;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_car")
    private Car firstCar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_pilot")
    private TeamMember secondPilot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_car")
    private Car secondCar;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AcceptStatus status;

    private String comment;

    /* ================================
     constructors
    ================================ */

    public RaceRegistration(@NotNull Team team, @NotNull Race race, @NotNull TeamMember firstPilot, @NotNull Car firstCar, TeamMember secondPilot, Car secondCar, @NotNull AcceptStatus status) {
        this.team = team;
        this.race = race;
        this.firstPilot = firstPilot;
        this.firstCar = firstCar;
        this.secondPilot = secondPilot;
        this.secondCar = secondCar;
        this.status = status;
    }
}
