package com.rogo.inv.iadprojf1.entity.race;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import com.rogo.inv.iadprojf1.entity.pitstop.PilotChange;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopRepair;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopService;
import com.rogo.inv.iadprojf1.entity.pitstop.PitStopTransfer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "races")
@Getter @Setter @NoArgsConstructor
public class Race {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Championship champ;

    @NotNull
    private LocalTime duration;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull @Min(1)
    private int laps;

    @NotNull @Min(1)
    private int maxParticipants;

    @NotNull @Column(length = 30)
    private String track;

    /* ================================
     values fetched from 'OneToMany' tables
    ================================ */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race_result")
    private List<RaceResult> raceResults = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_transfer")
    private List<PitStopTransfer> pitStopTransfers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_repair")
    private List<PitStopRepair> pitStopRepairs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pit_stop_service")
    private List<PitStopService> pitStopServices = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pilot_change")
    private List<PilotChange> pilotChanges = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race_registration")
    private List<RaceRegistration> raceRegistrations = new ArrayList<>();

    /* ================================
     constructors
    ================================ */
    public Race(@NotNull Championship champ, @NotNull LocalTime duration, @NotNull LocalDateTime dateTime, @NotNull @Min(1) int laps, @NotNull @Min(1) int maxParticipants, @NotNull String track) {
        this.champ = champ;
        this.duration = duration;
        this.dateTime = dateTime;
        this.laps = laps;
        this.maxParticipants = maxParticipants;
        this.track = track;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return laps == race.laps &&
                Objects.equal(champ, race.champ) &&
                Objects.equal(track, race.track);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(champ, laps, track);
    }
}
