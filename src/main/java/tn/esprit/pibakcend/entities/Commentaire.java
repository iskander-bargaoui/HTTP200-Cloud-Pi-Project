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
import javax.validation.constraints.NotEmpty;
import java.util.Set;


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

    @Column(name = "date_commented")
    private LocalDate dateCreationComm;

    @JoinColumn(name="id_pub")
    @ManyToOne (fetch = FetchType.LAZY)
    private Publication publication;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "commentaire", cascade = CascadeType.ALL)
    private Set<Like> likes;


}
