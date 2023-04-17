package com.example.pi.Repository;

import com.example.pi.entities.Commande;
import com.example.pi.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Long> {
    Panier findByUserId(Long id );
}
