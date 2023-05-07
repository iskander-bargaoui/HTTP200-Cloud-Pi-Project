package tn.esprit.pibakcend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.security.services.IChatMessageService;
import tn.esprit.pibakcend.security.services.INotification;
import tn.esprit.pibakcend.entities.ChatMessage;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "//localhost:4200")
@RequestMapping("")
public class ChatControoler {
    public IChatMessageService messageService;
    public INotification  notificationService ;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setTime(Date.from(Instant.now()));
        messageService.retrieveByIds(chatMessage.getSenderId(), chatMessage.getReceiverId());
        messageService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
       List<ChatMessage> messages = messageService.retrieveByIds(chatMessage.getSenderId(), chatMessage.getReceiverId());
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





