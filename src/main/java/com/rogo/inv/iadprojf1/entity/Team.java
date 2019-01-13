package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rogo.inv.iadprojf1.DBUtility;
import com.rogo.inv.iadprojf1.entity.cup.ConstrCupResult;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull  @Column(length = DBUtility.NAME_LEN, unique = true)
    private String name;

    @NotNull @Min(0)
    private long budget;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AcceptStatus status;

    private String comments;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team")
    @JsonManagedReference
    private List<TeamMember> teamMembers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team")
    @JsonManagedReference
    private List<Sponsoring> sponsorings = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team")
    @JsonManagedReference
    private List<Car> cars = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team")
    @JsonManagedReference
    private List<EngineStorage> engines = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team")
    @JsonManagedReference
    private List<ChassisStorage> chassis = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team")
    @JsonManagedReference
    private List<ElectronicsStorage> electronics = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team")
    private List<CarcaseStorage> carcases = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team")
    private List<ConstrCupResult> constrCupResults = new ArrayList<>();
//
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_places")
//    private List<PitStopPlace> pitStopPlaces = new ArrayList<>();

    /* ================================
     constructors
    ================================ */
//    public Team(@NotNull String name, @NotNull @Min(0) long budget, @NotNull AcceptStatus status, String comments) {
//        this.name = name;
//        this.budget = budget;
//        this.status = status;
//        this.comments = comments;
//    }
}