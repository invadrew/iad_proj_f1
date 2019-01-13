package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "sponsoring")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sponsoring {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sponsor_id")
    @JsonBackReference
    private Sponsor sponsor;

    @NotNull @Min(0)
    private long spMoney;

    private LocalDateTime date;

    /* ================================
     constructors
    ================================ */
    public Sponsoring(@NotNull Team team, @NotNull Sponsor sponsor, @NotNull @Min(0) long spMoney, LocalDateTime date) {
        this.team = team;
        this.sponsor = sponsor;
        this.spMoney = spMoney;
        this.date = date;
    }
}
