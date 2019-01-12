package com.rogo.inv.iadprojf1.entity;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.cup.WorldCupResult;
import com.rogo.inv.iadprojf1.entity.message.Chat;
import com.rogo.inv.iadprojf1.entity.message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull @Column(length = 24, unique = true)
    private String login;

    @NotNull @Column(length = 32)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserSpec userSpec;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Photo photo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AcceptStatus status;

    private String comments;

    /* ================================
     values fetched from 'OneToMany' tables
    ================================ */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "world_cup_result")
    private Set<WorldCupResult> worldCupResults = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private Set<Chat> chats = new HashSet<>();

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    /* ================================
     constructors
    ================================ */
    public User(@NotNull String login, @NotNull String password, @NotNull UserSpec userSpec, Photo photo, @NotNull AcceptStatus status, String comments) {
        this.login = login;
        this.password = password;
        this.userSpec = userSpec;
        this.photo = photo;
        this.status = status;
        this.comments = comments;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // id, login, userSpec
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equal(login, user.login) &&
                userSpec == user.userSpec;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, login, userSpec);
    }

    /* ================================
     enums
    ================================ */
    enum UserSpec{
        RACER,
        MANAGER,
        ADMIN,
        SPONSOR,
        CONSTRUCTOR,
        MECHANIC
    }
}
