package com.rogo.inv.iadprojf1.entity.cup;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.Season;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "championships")
@Getter @Setter @NoArgsConstructor
public class Championship {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @NotNull @Column(length = 25)
    private String name;

    @NotNull @Column(length = 30)
    private String country;

    @NotNull @Min(1)
    private long stageNum;

    /* ================================
     constructors
    ================================ */
    public Championship(@NotNull Season season,@NotNull String name, @NotNull String country, @NotNull @Min(1) long stageNum) {
        this.season = season;
        this.name = name;
        this.country = country;
        this.stageNum = stageNum;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // id, season, name, country, stageNum
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Championship that = (Championship) o;
        return id == that.id &&
                stageNum == that.stageNum &&
                Objects.equal(season, that.season) &&
                Objects.equal(name, that.name) &&
                Objects.equal(country, that.country);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, season, name, country, stageNum);
    }
}

