package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Messages;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    AccountService accountService;
    MessageService messageService;

    @PostMapping("/register")
    public Account register(@RequestBody Account account){
        return new Account();
    }

    @PostMapping("/login")
    public Account login(@RequestBody Account account){
        return new Account();
    }

    @PostMapping("/messages")
    public Message postMessage(@RequestBody Message message){
        return new Message();
    }

    @GetMapping("/messages")
    public List<Message> getMessages(){
        return new ArrayList<Message>();
    }

    @GetMapping("/messages/{messageId}")
    public Message getMessage(@PathVariable int messageId){
        return new Message();
    }

    @DeleteMapping("/messages/{messageId}")
    public int deleteMessage(@PathVariable int messageId){
        return 0;
    }

    @PatchMapping("/messages/{messageId}")
    public Message updateMessage(@PathVariable int messageId){
        return new Message();
    }

    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getMessageByAccount(@PathVariable int accountId){
        return new ArrayList<Message>();
    }

    

}
