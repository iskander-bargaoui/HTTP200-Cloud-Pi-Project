package tn.esprit.pibakcend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.ChatMessageService;
import tn.esprit.pibakcend.Service.IChatMessageService;
import tn.esprit.pibakcend.Service.INotification;
import tn.esprit.pibakcend.entities.ChatMessage;
import tn.esprit.pibakcend.entities.Reservation;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class ChatControoler {
    public IChatMessageService messageService;
    public INotification  notificationService ;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setTime(Date.from(Instant.now()));
        messageService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
       // List<ChatMessage> messages = messageService.getAllMessages();
        return chatMessage;
    }

    @PostMapping("/AddMesg")
    public ChatMessage  saveMessage(@RequestBody  ChatMessage ChatMessage)  {
         return messageService.saveMessage(ChatMessage);
    }

    @GetMapping("/retrieveByIds/{receiver_id}/{sender_id}")
    public List<ChatMessage> retrieveByIds(@PathVariable("sender_id") Long sender_id , @PathVariable("receiver_id") Long receiver_id){
        return messageService.retrieveByIds(sender_id,receiver_id);
    }



    }





