package tn.esprit.pibakcend.security.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.ChatMessageRepository;
import tn.esprit.pibakcend.entities.ChatMessage;

import javax.sql.DataSource;
import java.util.List;


@Service
@AllArgsConstructor
public class ChatMessageService implements IChatMessageService {

    private final DataSource dataSource;
    public ChatMessageRepository messageRepository;
    public List<ChatMessage> a ;
    public ChatMessage saveMessage(ChatMessage ChatMessage) {
       return messageRepository.save(ChatMessage);
    }



    @Override
    public List<ChatMessage> retrieveByIds (Long sender_id ,Long receiver_id ){
        return messageRepository.retrieveByIds(sender_id ,receiver_id) ;
    }


    // autres méthodes de service pour les opérations CRUD
}