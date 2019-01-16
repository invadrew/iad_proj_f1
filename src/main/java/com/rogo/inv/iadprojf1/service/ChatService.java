package com.rogo.inv.iadprojf1.service;

import com.rogo.inv.iadprojf1.entity.message.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> findAll();

    Chat save(Chat entity);

    void delete(Chat entity);

    Chat findById(int id);

    List<Chat> findAllUserChats(int id);
}