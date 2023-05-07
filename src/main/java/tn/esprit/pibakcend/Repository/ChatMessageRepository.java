package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.pibakcend.entities.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    @Query("SELECT mesg FROM ChatMessage mesg WHERE ((mesg.senderId=:sender_id AND mesg.receiverId=:receiver_id )OR (mesg.senderId=:receiver_id AND mesg.receiverId=:sender_id )) order by  mesg.time ASC")
    public List<ChatMessage> retrieveByIds(@Param("sender_id") Long sender_id ,@Param("receiver_id") Long receiver_id);



}

