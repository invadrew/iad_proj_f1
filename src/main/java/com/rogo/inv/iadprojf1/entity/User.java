package com.rogo.inv.iadprojf1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.DBUtility;
import com.rogo.inv.iadprojf1.entity.message.Chat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor
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

    @OneToOne(/* fetch = FetchType.LAZY, */ cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AcceptStatus status;

    private String comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "buy_status")
    private AcceptStatus buyStatus;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private Set<Chat> chats = new HashSet<>();

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

    public User(@NotNull String login, @NotNull String password, @NotNull Spec spec, Photo photo, @NotNull AcceptStatus status, String comments, AcceptStatus buyStatus) {
        this.login = login;
        this.password = password;
        this.spec = spec;
        this.photo = photo;
        this.status = status;
        this.comments = comments;
        this.buyStatus = buyStatus;
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
        MECHANIC,
        ANONYMOUS
    }
}
