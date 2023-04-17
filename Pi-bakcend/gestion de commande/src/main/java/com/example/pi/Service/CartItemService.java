package com.example.pi.Service;

import com.example.pi.entities.User;
import com.example.pi.entities.Produit;
import com.example.pi.entities.ShoppingCart;

public interface CartItemService {
    ShoppingCart addItemToCart(Produit produit, int quantity, User user);

    ShoppingCart updateItemInCart(Produit produit, int quantity, User user);

    ShoppingCart deleteItemFromCart(Produit produit, User user);

}
