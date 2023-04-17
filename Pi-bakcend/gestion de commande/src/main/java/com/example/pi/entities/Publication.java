package com.example.pi.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Publication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPub;
    private String contenuPub;
    private String titrePub;
    @Enumerated(EnumType.STRING)
    private Visibilite vis;
    @Temporal(TemporalType.DATE)
    private Date dateCreationPub;
}
