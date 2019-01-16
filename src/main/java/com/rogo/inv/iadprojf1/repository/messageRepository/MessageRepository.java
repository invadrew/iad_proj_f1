package com.rogo.inv.iadprojf1.repository.messageRepository;

import com.rogo.inv.iadprojf1.entity.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findById(int id);

    @Query(value = "SELECT * FROM messages m WHERE (m.chat_id = ?1)", nativeQuery = true)
    List<Message> findAllChatMessages(int id);
}
