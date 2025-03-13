package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
@Transactional
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> findAllMessages(){
        return messageRepository.findAll();
    }
    
    public List<Message> findMessagesByAccount(int accountId){
        return messageRepository.findMessagesByPostedBy(accountId);
    }

    public Message findMessageById(int messageId){
        return messageRepository.findMessageByMessageId(messageId);
    }

    public int deleteMessage(int messageId){
        return messageRepository.deleteByMessageId(messageId);
    }

    public int updateMessage(int messageId, String messageText){
        Optional<Message> om = messageRepository.findById(messageId);
        if(om.isPresent()){
            Message m = om.get();
            m.setMessageText(messageText);
            messageRepository.save(m);
            return 1;
        }
        return 0;
    }
}
