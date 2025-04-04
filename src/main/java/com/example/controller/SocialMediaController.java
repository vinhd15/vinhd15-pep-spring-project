package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account){
        if (account.getUsername().isBlank() || account.getPassword().length() < 4) {
            return ResponseEntity.badRequest().body("Invalid account details"); // 400 Bad Request
        }
        if(accountService.hasUsername(account.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username Already exists");
        }
        Account savedAccount = accountService.saveAccount(account);
        return ResponseEntity.ok(savedAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        Account verified = accountService.verifyAccount(account);
        if (verified != null) {
            return ResponseEntity.ok(verified);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @PostMapping("/messages")
    public ResponseEntity<?> postMessage(@RequestBody Message message){
        if(message.getMessageText().isBlank() ||
            message.getMessageText().length() > 255 ||
            accountService.findAccountById(message.getPostedBy()) == null){
                return ResponseEntity.status(400).body(null);
            }
        Message saved = messageService.addMessage(message);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getMessages(){
        return messageService.findAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public Message getMessage(@PathVariable int messageId){
        return messageService.findMessageById(messageId);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable int messageId){
       int rows = messageService.deleteMessage(messageId);
       return rows > 0 ? ResponseEntity.ok(rows) : ResponseEntity.ok().build();
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable int messageId, @RequestBody Message message){
        String messageText = message.getMessageText();
        if (messageText != null) {
            if(messageText.isEmpty() || messageText.isBlank() || messageText.length() > 255 ){
                return ResponseEntity.status(400).body(null);
            }
        
        
            int rows = messageService.updateMessage(messageId, messageText);
            if(rows > 0){
                return ResponseEntity.ok(rows);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getMessageByAccount(@PathVariable int accountId){
        return messageService.findMessagesByAccount(accountId);
    }

    

}
