package tn.esprit.pibakcend.security.services;

import tn.esprit.pibakcend.entities.ChatMessage;

import java.util.List;

public interface IChatMessageService {
    public ChatMessage saveMessage(ChatMessage ChatMessage);
    public List<ChatMessage> retrieveByIds (Long senderId ,Long receiverId ) ;
}
