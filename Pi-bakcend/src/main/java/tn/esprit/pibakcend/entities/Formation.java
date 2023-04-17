package tn.esprit.pibakcend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormation;
    private String titreFormation ;
    private String description;
    private String organisateur;
    @Temporal(TemporalType.DATE)
    private Date dateDebutFor;
    @Temporal(TemporalType.DATE)
    private Date dateFinFor;

    @ManyToMany (mappedBy = "formations")
    @JsonIgnore
    private Set<Evenement> evenementss;


}
