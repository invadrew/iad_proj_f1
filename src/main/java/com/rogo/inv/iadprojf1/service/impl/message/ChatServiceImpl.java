package com.rogo.inv.iadprojf1.service.impl.message;
import com.rogo.inv.iadprojf1.entity.message.Chat;
import com.rogo.inv.iadprojf1.repository.messageRepository.ChatRepository;
import com.rogo.inv.iadprojf1.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("chatService")
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatRepository repository;

    @Override
    public List<Chat> findAll() {
        return repository.findAll();
    }

    @Override
    public Chat save(Chat entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Chat entity) {
        repository.delete(entity);
    }

    @Override
    public Chat findById(int id) {
        return repository.findById(id);
    }

    @Override @Transactional
    public List<Chat> findAllUserChats(int id) {return repository.findAllUserChats(id); }
}
