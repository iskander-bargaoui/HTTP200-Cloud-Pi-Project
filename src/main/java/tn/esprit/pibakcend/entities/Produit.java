package tn.esprit.pibakcend.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit;
    private int idCategorieProduit;
    private int idFournisseur;

    private String nomProduit;
    private float prixProduit;
    private String descriptionProduit;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", nullable = false, columnDefinition = "mediumblob")
    private byte[] image;
}
