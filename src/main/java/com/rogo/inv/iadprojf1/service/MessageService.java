package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.message.Chat;
import com.rogo.inv.iadprojf1.entity.message.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();

    Message save(Message entity);

    void delete(Message entity);

    Message findById(int id);

    List<Message> findAllChatMessages(int id);
}