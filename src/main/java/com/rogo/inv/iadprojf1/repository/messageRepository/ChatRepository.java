package com.rogo.inv.iadprojf1.repository.messageRepository;

import com.rogo.inv.iadprojf1.entity.message.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Chat findById(int id);
}
