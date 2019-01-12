package com.rogo.inv.iadprojf1.entity.cup;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class ConstrCupResultPK {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "racer_id")
    private Team team;

    /* ================================
     constructors
    ================================ */
    public ConstrCupResultPK(@NotNull Season season, @NotNull Team racer) {
        this.season = season;
        this.team = racer;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // season, racer
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstrCupResultPK that = (ConstrCupResultPK) o;
        return Objects.equal(season, that.season) &&
                Objects.equal(team, that.team);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(season, team);
    }
}
