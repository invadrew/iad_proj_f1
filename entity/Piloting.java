package com.rogo.inv.iadprojf1.entity;

import com.google.common.base.Objects;
import com.google.gson.Gson;
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
@Getter @Setter @NoArgsConstructor
public class Piloting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne(mappedBy = "race_result", fetch = FetchType.LAZY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Car car;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private User racer;

    /* ================================
     constructors
    ================================ */
    public Piloting(@NotNull Car car, @NotNull User racer) {
        this.car = car;
        this.racer = racer;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // car, racer
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piloting piloting = (Piloting) o;
        return Objects.equal(car, piloting.car) &&
                Objects.equal(racer, piloting.racer);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(car, racer);
    }
}
