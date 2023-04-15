package tn.esprit.pibakcend.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ChatMessage {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
public long idmessage ;

    public String content;
    public String sender;

    public Long senderId;
    public Long receiverId;

    public LocalDateTime time;
    @OneToMany
    public List<User> users ;

}