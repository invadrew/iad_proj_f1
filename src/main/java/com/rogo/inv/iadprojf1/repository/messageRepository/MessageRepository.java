package com.rogo.inv.iadprojf1.repository.messageRepository;

import com.rogo.inv.iadprojf1.entity.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findById(int id);
}
