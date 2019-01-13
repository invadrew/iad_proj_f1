package com.rogo.inv.iadprojf1.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @NotNull
    private LocalDateTime time;

    @NotNull
    private boolean isRead;

    @NotNull
    private String messText;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @NotNull
    private boolean result;

    /* ================================
     constructors
    ================================ */
    public Message(@NotNull User author, @NotNull Chat chat, @NotNull LocalDateTime time, @NotNull boolean isRead, @NotNull String messText, @NotNull MessageType type, @NotNull boolean result) {
        this.author = author;
        this.chat = chat;
        this.time = time;
        this.isRead = isRead;
        this.messText = messText;
        this.type = type;
        this.result = result;
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
