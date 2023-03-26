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
@AllArgsConstructor
@ToString
@Table(name = "commentaire")
@Entity
public class Commentaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComm; // Clé primaire
    @Column(nullable = false)
    private String contenuComm;

    private String imageComm;
    //@Temporal(TemporalType.DATE)

    @Column(name = "date_commented")
    private LocalDate dateCreationComm;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    private Publication publication;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "commentaire", cascade = CascadeType.ALL)
    private List<Like> likes;


}
