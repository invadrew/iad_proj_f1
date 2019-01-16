package com.rogo.inv.iadprojf1.service.impl.message;
import com.rogo.inv.iadprojf1.entity.message.Chat;
import com.rogo.inv.iadprojf1.entity.message.Message;
import com.rogo.inv.iadprojf1.repository.messageRepository.MessageRepository;
import com.rogo.inv.iadprojf1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository repository;

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Message save(Message entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Message entity) {
        repository.delete(entity);
    }

    @Override
    public Message findById(int id) {
        return repository.findById(id);
    }

    @Override @Transactional
    public List<Message> findAllChatMessages(int id) {return repository.findAllChatMessages(id); }
}
