package com.rogo.inv.iadprojf1.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.rogo.inv.iadprojf1.DBUtility;
import com.rogo.inv.iadprojf1.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chats")
@Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Chat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull @Column(length = DBUtility.NAME_LEN)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "chats")
    private Set<User> users = new HashSet<>();

    /* ================================
     constructors
    ================================ */
    public Chat(@NotNull String name) {
        this.name = name;
    }

}
