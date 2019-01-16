package com.rogo.inv.iadprojf1.entity.cup;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.Season;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "championships")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Championship {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    @JsonBackReference
    private Season season;

    @NotNull @Column(length = 25)
    private String name;

    @NotNull @Column(length = 30)
    private String country;

    @NotNull @Min(1)
    private int stageNum;

    /* ================================
     constructors
    ================================ */
    public Championship(@NotNull Season season,@NotNull String name, @NotNull String country, @NotNull @Min(1) int stageNum) {
        this.season = season;
        this.name = name;
        this.country = country;
        this.stageNum = stageNum;
    }

    public Championship() {}
}

