package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.ChatMessage;
import tn.esprit.pibakcend.entities.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface IChatMessageService  {
    public ChatMessage saveMessage(ChatMessage ChatMessage);

    public List <ChatMessage> getAllMessagesBySenderAndReceiverIds(long senderId, long receiverId);

    public List<ChatMessage> retrieveByIds (Long senderId ,Long receiverId ) ;
}
