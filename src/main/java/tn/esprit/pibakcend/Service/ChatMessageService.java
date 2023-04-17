package tn.esprit.pibakcend.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.ChatMessageRepository;
import tn.esprit.pibakcend.entities.ChatMessage;
import tn.esprit.pibakcend.entities.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ChatMessageService implements IChatMessageService {

    private final DataSource dataSource;
    public ChatMessageRepository messageRepository;
    @Override
    public ChatMessage saveMessage(ChatMessage ChatMessage) {
       return messageRepository.save(ChatMessage);
    }

    @Override
    public List<ChatMessage> retrieveByIds (Long sender_id ,Long receiver_id ){
        return messageRepository.retrieveByIds(sender_id ,receiver_id) ;
    }


    // autres méthodes de service pour les opérations CRUD
}