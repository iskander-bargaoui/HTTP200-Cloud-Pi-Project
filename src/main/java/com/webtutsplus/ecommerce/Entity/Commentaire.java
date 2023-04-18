package com.webtutsplus.ecommerce.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
}
