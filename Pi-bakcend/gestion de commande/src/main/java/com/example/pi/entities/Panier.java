package com.example.pi.entities;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Panier implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long userId;

    //@OneToMany(mappedBy = "Panier", cascade = CascadeType.ALL)
    //private Set<Produit> produits;
    @Column(name = "total_price")
    private BigDecimal tprix;

    @Column(name="quantity")
    private int quantity;

    @Column(name="product_id")
    private Long productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id")
    @JsonIgnore
    @JsonBackReference
    private Commande commande;



}
