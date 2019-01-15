package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rogo.inv.iadprojf1.DBUtility;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sponsors")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sponsor {

    @Id
    private int userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonBackReference
    private User user;

    @NotNull @Column(length = DBUtility.NAME_LEN)
    private String name;

    @NotNull @Min(0)
    private long budget;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "sponsor",
            orphanRemoval = true)
    @JsonManagedReference
    private List<Sponsoring> sponsoring = new ArrayList<>();

    /* ================================
     constructors
    ================================ */

    public Sponsor(int userId, User user, @NotNull String name, @NotNull @Min(0) long budget, List<Sponsoring> sponsoring) {
        this.userId = userId;
        this.user = user;
        this.name = name;
        this.budget = budget;
        this.sponsoring = sponsoring;
    }
}