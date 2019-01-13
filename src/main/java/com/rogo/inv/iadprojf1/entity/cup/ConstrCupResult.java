package com.rogo.inv.iadprojf1.entity.cup;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "constr_cup_result")
@IdClass(ConstrCupResultPK.class)
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConstrCupResult {
    @Id @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    @JsonBackReference
    private Season season;

    @Id @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    @NotNull @Min(1)
    private int place;

    @NotNull @Min(0)
    private int points;

    /* ================================
     constructors
    ================================ */
    public ConstrCupResult(@NotNull Season season, @NotNull Team team, @NotNull @Min(0) int place, @NotNull @Min(0) int points) {
        this.season = season;
        this.team = team;
        this.place = place;
        this.points = points;
    }
}


