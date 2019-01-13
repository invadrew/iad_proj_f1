package com.rogo.inv.iadprojf1.entity.cup;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.Season;
import com.rogo.inv.iadprojf1.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "world_cup_result")
@IdClass(WorldCupResultPK.class)
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorldCupResult {
    @Id @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    @JsonBackReference
    private Season season;

    @Id @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "racer_id")
    private User racer;

    @NotNull @Min(1)
    private int place;

    @NotNull @Min(0)
    private int points;

    /* ================================
     constructors
    ================================ */
    public WorldCupResult(@NotNull Season season, @NotNull User racer, @NotNull @Min(1) int place, @NotNull @Min(0) int points) {
        this.season = season;
        this.racer = racer;
        this.place = place;
        this.points = points;
    }
}


