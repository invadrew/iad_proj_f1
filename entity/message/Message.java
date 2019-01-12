package com.rogo.inv.iadprojf1.entity.message;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter @Setter @NoArgsConstructor
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private User author;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    private Chat chat;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private boolean isRead;

    @NotNull
    private String messText;

    @NotNull
    private MessageType type;

    @NotNull
    private boolean result;

    /* ================================
     constructors
    ================================ */
    public Message(@NotNull User author, @NotNull Chat chat, @NotNull LocalDateTime date, @NotNull boolean isRead, @NotNull String messText, @NotNull MessageType type, @NotNull boolean result) {
        this.author = author;
        this.chat = chat;
        this.date = date;
        this.isRead = isRead;
        this.messText = messText;
        this.type = type;
        this.result = result;
    }

    /* ================================
     overridden Object methods
    ================================ */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // id, author, chat, date, type, result
    // generated with Guava
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                result == message.result &&
                Objects.equal(author, message.author) &&
                Objects.equal(chat, message.chat) &&
                Objects.equal(date, message.date) &&
                type == message.type;
    }

    // generated with Guava
    @Override
    public int hashCode() {
        return Objects.hashCode(id, author, chat, date, type, result);
    }

    /* ================================
     enums
    ================================ */
    enum MessageType{
        ORDINARY,
        TEAM_INVITE,
        JOIN_REQUEST,
        TEAM_CREATE,
        BUY_PERMISSION
    }
}
