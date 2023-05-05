package tn.esprit.pibakcend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "commentaire")
@Entity
public class Commentaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComm; // Clé primaire
    @NotEmpty
    private String contenuComm;

    private Integer likeCount;
    private Integer dislikeCount;
    @Column(name = "date_commented")
    private LocalDate dateCreationComm;
    @JsonIgnore

    @JoinColumn(name="id_pub")
    @ManyToOne (fetch = FetchType.LAZY)
    private Publication publication;
    @JsonIgnore

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "commentaire", cascade = CascadeType.ALL)
    private Set<Like> likes;



}
