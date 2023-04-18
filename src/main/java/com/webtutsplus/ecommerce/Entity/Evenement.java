package com.webtutsplus.ecommerce.Entity;


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
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvenement;
    private String titreEvennement ;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String Lieu;

    //@ManyToMany (mappedBy = "evenements")
    @JsonIgnore
    //private Set<User> utilisateurs;

    @ManyToMany
    private Set<Formation> formations;

}
