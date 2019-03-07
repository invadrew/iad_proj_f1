package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rogo.inv.iadprojf1.entity.cup.Championship;
import com.rogo.inv.iadprojf1.entity.cup.ConstrCupResult;
import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seasons")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Season {
    @Id @Min(1950)
    private int year;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    @JsonManagedReference
    private List<WorldCupResult> worldCupResults = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    @JsonManagedReference
    private List<ConstrCupResult> constrCupResults = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    private List<Championship> championships = new ArrayList<>();

    public Season(int year) {
        this.year = year;
    }

}
