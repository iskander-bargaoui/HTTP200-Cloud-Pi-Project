package com.example.pi.Service;

import com.example.pi.Repository.CartItemRepository;
import com.example.pi.Repository.ShoppingCartRepository;
import com.example.pi.entities.CartItem;
import com.example.pi.entities.Produit;
import com.example.pi.entities.ShoppingCart;
import com.example.pi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository itemRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Override
    public ShoppingCart addItemToCart(Produit produit, int quantity, User user) {
        ShoppingCart cart = user.getShoppingCart();

        if (cart == null) {
            cart = new ShoppingCart();
        }
        Set<CartItem> cartItems = cart.getCartItem();
        CartItem cartItem = findCartItem(cartItems, produit .getId());
        if (cartItems == null) {
            cartItems = new HashSet<>();
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(produit);
                cartItem.setTotalPrice(quantity * produit.getCostPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                itemRepository.save(cartItem);
            }
        } else {
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(produit);
                cartItem.setTotalPrice(quantity * produit.getCostPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                itemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(cartItem.getTotalPrice() + ( quantity * produit.getCostPrice()));
                itemRepository.save(cartItem);
            }
        }
        cart.setCartItem(cartItems);

        int totalItems = totalItems(cart.getCartItem());
        double totalPrice = totalPrice(cart.getCartItem());

        cart.setTotalPrices(totalPrice);
        cart.setTotalItems(totalItems);
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart updateItemInCart(Produit produit, int quantity, User user) {
        ShoppingCart cart = user.getShoppingCart();

        Set<CartItem> cartItems = cart.getCartItem();

        CartItem item = findCartItem(cartItems, produit.getId());

        item.setQuantity(quantity);
        item.setTotalPrice(quantity * produit.getCostPrice());

        itemRepository.save(item);

        int totalItems = totalItems(cartItems);
        double totalPrice = totalPrice(cartItems);

        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);

        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart deleteItemFromCart(Produit produit, User  user) {
        ShoppingCart cart = user.getShoppingCart();

        Set<CartItem> cartItems = cart.getCartItem();

        CartItem item = findCartItem(cartItems, produit.getId());

        cartItems.remove(item);

        itemRepository.delete(item);

        double totalPrice = totalPrice(cartItems);
        int totalItems = totalItems(cartItems);

        cart.setCartItem(cartItems);
        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);

        return cartRepository.save(cart);
    }

    private CartItem findCartItem(Set<CartItem> cartItems, Long productId) {
        if (cartItems == null) {
            return null;
        }
        CartItem cartItem = null;

        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productId) {
                cartItem = item;
            }
        }
        return cartItem;
    }

    private int totalItems(Set<CartItem> cartItems){
        int totalItems = 0;
        for(CartItem item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private double totalPrice(Set<CartItem> cartItems){
        double totalPrice = 0.0;

        for(CartItem item : cartItems){
            totalPrice += item.getTotalPrice();
        }

        return totalPrice;
    }
}
