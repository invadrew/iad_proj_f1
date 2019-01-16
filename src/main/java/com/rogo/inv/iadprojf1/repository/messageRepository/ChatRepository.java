package com.rogo.inv.iadprojf1.repository.messageRepository;

import com.rogo.inv.iadprojf1.entity.message.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Chat findById(int id);

    @Query(value = "SELECT c.chat_id FROM user_chat c WHERE (c.user_id = ?1)", nativeQuery = true)
    List<Chat> findAllUserChats(int id);
}
