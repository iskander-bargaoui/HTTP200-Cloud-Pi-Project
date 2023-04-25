package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.OrderItem;

import java.util.List;

public interface IOrderItem {
    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItemsByOrderId(Long orderId);

    OrderItem getOrderItemById(Long id);
}
