package com.rogo.inv.iadprojf1.entity.race;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class RaceRegistrationPK {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Team team;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Race race;

    /* ================================
     constructors
    ================================ */
    public RaceRegistrationPK(@NotNull Team team, @NotNull Race race) {
        this.team = team;
        this.race = race;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // team, race
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceRegistrationPK that = (RaceRegistrationPK) o;
        return Objects.equal(team, that.team) &&
                Objects.equal(race, that.race);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(team, race);
    }
}
