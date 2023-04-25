package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.configuration.CouponException;
import tn.esprit.pibakcend.entities.*;

import java.util.List;

public interface IOrder {
    Order createOrder(User user, List<OrderItem> orderItems);
    List<Order> getOrdersByUser(User user);
    Order getOrderById(Long id);
    Double calculateTotalPrice(List<OrderItem> orderItems);
    List<ERole> getUserRoles(User user);
    //Purchase createPurchase(User user, Double amount);
    boolean isEligibleForCoupon(User user, Double amount);
    Order applyCoupon(User user, Order order, String couponCode) throws CouponException;

    Order save(Order order);
}

