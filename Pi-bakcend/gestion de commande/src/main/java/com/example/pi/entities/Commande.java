package com.example.pi.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Data
@AllArgsConstructor // Contructor with params
@NoArgsConstructor // Default Constructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// Clé primaire
    private Long userId;
    private Long commandeId;
    @Column(name = "date_commande")
    private LocalDate dateCommande;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private CommandeStatus commandeStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livreur_id")
    @JsonIgnore
    private Livreur livreur;


    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Panier> paniers = new ArrayList<>();


    }







