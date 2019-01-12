package com.rogo.inv.iadprojf1.entity.message;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
@Getter @Setter @NoArgsConstructor
public class Chat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull @Column(length = 50)
    private String name;

    /* ================================
     values fetched from 'OneToMany' tables
    ================================ */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sponsoring")
    private List<Message> messages = new ArrayList<>();

    /* ================================
     constructors
    ================================ */
    public Chat(@NotNull String name) {
        this.name = name;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // id, name
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return id == chat.id &&
                Objects.equal(name, chat.name);
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}
