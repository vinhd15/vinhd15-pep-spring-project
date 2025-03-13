package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

    List<Message> findMessagesByPostedBy(int postedBy);

    Message findMessageByMessageId(int messageId);

    @Modifying
    @Query("Delete from Message m where m.messageId = :messageId")
    int deleteByMessageId(@Param("messageId") int messageId);

    
}
