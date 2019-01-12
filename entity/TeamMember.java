package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.DBUtility;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team_members")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamMember {

    @Id
    private int userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @NotNull @Column(length = DBUtility.NAME_LEN)
    private String name;

    @NotNull @Column(length = DBUtility.NAME_LEN)
    private String surname;

    @NotNull
    private boolean canBuy;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonBackReference
    private Team team;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pilot_change")
//    private List<PilotChange> worldCupResults = new ArrayList<>();
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "piloting",
//            joinColumns = @JoinColumn(name = "pilot_id"),
//            inverseJoinColumns = @JoinColumn(name = "racer_id")
//    )
//    private List<Car> cars = new ArrayList<>();

    /* ================================
     constructors
    ================================ */
//    public TeamMember(User user, @NotNull String name, @NotNull String surname, @NotNull boolean canBuy, @NotNull Team team) {
//        this.user = user;
//        this.name = name;
//        this.surname = surname;
//        this.canBuy = canBuy;
//        this.team = team;
//    }
}
