package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rogo.inv.iadprojf1.DBUtility;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull  @Column(length = DBUtility.NAME_LEN, unique = true)
    private String name;

    @NotNull @Min(0)
    private long budget;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AcceptStatus status;

    private String comments;

    @OneToMany(mappedBy = "team")
    @JsonManagedReference
    private Set<TeamMember> teamMembers = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cars")
//    private Set<Car> cars = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "constr_cup_result")
//    private Set<ConstrCupResult> constrCupResults = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engine_storage")
//    private List<EngineStorage> engines = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chassis_storage")
//    private List<ChassisStorage> chassis = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electronics_storage")
//    private List<ElectronicsStorage> electronics = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carcase_storage")
//    private List<CarcaseStorage> carcases = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sponsoring")
//    private List<Sponsoring> sponsorings = new ArrayList<>();
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
