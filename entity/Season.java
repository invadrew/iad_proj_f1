package com.rogo.inv.iadprojf1.entity;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import com.rogo.inv.iadprojf1.entity.cup.ConstrCupResult;
import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seasons")
@Getter @Setter @NoArgsConstructor
public class Season {
    @Id @Min(1950)
    private long year;

    /* ================================
     values fetched from 'OneToMany' tables
    ================================ */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "world_cup_result")
    private List<WorldCupResult> worldCupResults = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "constr_cup_result")
    private List<ConstrCupResult> constrCupResults = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "championships")
    private List<Championship> championships = new ArrayList<>();

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // year
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return year == season.year;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(year);
    }
}
