package com.example.pi.RestController;

import com.example.pi.Service.CartItemService;
import com.example.pi.entities.CartItem;
import com.example.pi.entities.Produit;
import com.example.pi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Carte")
@CrossOrigin(origins="http://localhost:4200")
public class CateItemRestcontroller {
    @Autowired
    private CartItemService cartService;


    @PostMapping("/{productId}")
    public ResponseEntity<Void> addToCart(@PathVariable Long productId) {
        Produit produit = produitService.findById(productId);
        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        // On récupère l'utilisateur courant depuis la session ou la base de données
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // On vérifie si l'utilisateur a déjà un panier ou s'il faut en créer un
        CartItem cartItem = CartItemService.findByProduitAndUser(produit, currentUser);
        if (cartItem == null) {
            cartItem = new CartItem(produit, 1, currentUser);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        CartItemService.save(  cartItem);
        return ResponseEntity.ok().build();
    }


}
