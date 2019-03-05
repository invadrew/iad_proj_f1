package com.rogo.inv.iadprojf1.entity.race;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "races")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Race {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "champ_id")
    private Championship champ;

    private LocalTime duration;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull @Min(1)
    private int laps;

    @NotNull @Min(1)
    private int maxParticipants;

    @NotNull @Column(length = 30)
    private String track;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race_result")
//    private List<RaceResult> raceResults = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_transfer")
//    private List<PitStopTransfer> pitStopTransfers = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_repair")
//    private List<PitStopRepair> pitStopRepairs = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_service")
//    private List<PitStopService> pitStopServices = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pilot_change")
//    private List<PilotChange> pilotChanges = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race_registration")
//    private List<RaceRegistration> raceRegistrations = new ArrayList<>();

    /* ================================
     constructors
    ================================ */
    public Race(@NotNull Championship champ, LocalTime duration, @NotNull LocalDateTime dateTime, @NotNull @Min(1) int laps, @NotNull @Min(1) int maxParticipants, @NotNull String track) {
        this.champ = champ;
        this.duration = duration;
        this.dateTime = dateTime;
        this.laps = laps;
        this.maxParticipants = maxParticipants;
        this.track = track;
    }
}
