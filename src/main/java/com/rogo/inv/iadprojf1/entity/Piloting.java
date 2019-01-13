package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.google.gson.Gson;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "piloting",
        uniqueConstraints = @UniqueConstraint(columnNames = {"car_id", "racer_id"})
        )
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Piloting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@OneToOne(mappedBy = "race_result", fetch = FetchType.LAZY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Car car;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "racer_id")
    @JsonBackReference
    private User racer;

    /* ================================
     constructors
    ================================ */
    public Piloting(@NotNull Car car, @NotNull User racer) {
        this.car = car;
        this.racer = racer;
    }
}
