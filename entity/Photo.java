package com.rogo.inv.iadprojf1.entity;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "photos")
@Getter @Setter @NoArgsConstructor
public class Photo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne(fetch = FetchType.LAZY)
    private long id;

    @NotNull @Column(unique = true)
    private String path;

    /* ================================
     constructors
    ================================ */
    public Photo(@NotNull String path) {
        this.path = path;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equal(path, photo.path);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(path);
    }
}
