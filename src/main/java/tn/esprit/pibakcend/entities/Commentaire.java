package tn.esprit.pibakcend.entities;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Commentaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComm; // ClÃ© primaire
    private String contenuComm;
    private String imageComm;
    @Temporal(TemporalType.DATE)
    private Date dateCreationComm;

    @OneToMany(mappedBy = "commentaire", cascade = CascadeType.ALL)
    private List<Publication> publications = new ArrayList<>();
}
