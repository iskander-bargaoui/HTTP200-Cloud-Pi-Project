package tn.esprit.pibakcend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    private Date dateCommande;
    private String etatCommande;
    private Integer quantiteCommandee;
    private Double prixTotal;
    private String informationsLivraison;
}
