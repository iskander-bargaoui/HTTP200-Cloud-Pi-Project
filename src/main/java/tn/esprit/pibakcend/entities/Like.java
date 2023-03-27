package tn.esprit.pibakcend.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "likes")
public class Like implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlike;

    private Boolean likeStatus;
    private Boolean dislikeStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="id_pub")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name="id_comm")
    private Commentaire commentaire;

    public Like(Boolean likeStatus, Boolean dislikeStatus, User user, Publication publication, Commentaire commentaire) {
        this.likeStatus = likeStatus;
        this.dislikeStatus = dislikeStatus;
        this.user = user;
        this.publication = publication;
        this.commentaire = commentaire;
    }
}

