package com.example.pi.Repository;

import com.example.pi.entities.CartItem;
import com.example.pi.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProductAndUser(Produit produit, User user);

}
