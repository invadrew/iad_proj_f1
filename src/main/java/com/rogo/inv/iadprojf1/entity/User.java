package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rogo.inv.iadprojf1.DBUtility;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull @Column(length = DBUtility.LOGIN_LEN, unique = true)
    private String login;

    @NotNull @Column(length = DBUtility.PASSWORD_LEN)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Spec spec;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    private Photo photo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AcceptStatus status;

    private String comments;

    /* ================================
     constructors
    ================================ */
    public User(@NotNull String login, @NotNull String password, @NotNull Spec spec, Photo photo, @NotNull AcceptStatus status, String comments) {
        this.login = login;
        this.password = password;
        this.spec = spec;
        this.photo = photo;
        this.status = status;
        this.comments = comments;
    }

    /* ================================
         enums
        ================================ */
    public enum Spec {
        RACER,
        MANAGER,
        ADMIN,
        SPONSOR,
        CONSTRUCTOR,
        MECHANIC
    }
}
