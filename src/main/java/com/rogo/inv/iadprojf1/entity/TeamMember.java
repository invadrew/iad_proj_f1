package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rogo.inv.iadprojf1.DBUtility;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team_members")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamMember implements Serializable {
    @Id @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull @Column(length = DBUtility.NAME_LEN)
    private String name;

    @NotNull @Column(length = DBUtility.NAME_LEN)
    private String surname;

    @NotNull
    private boolean canBuy;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "piloting",
            joinColumns = @JoinColumn(name = "racer_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    @JsonManagedReference
    private List<Car> cars = new ArrayList<>();

    /* ================================
     constructors
    ================================ */
    public TeamMember(@NotNull User user, @NotNull String name, @NotNull String surname, @NotNull boolean canBuy, @NotNull Team team) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.canBuy = canBuy;
        this.team = team;
    }
}
