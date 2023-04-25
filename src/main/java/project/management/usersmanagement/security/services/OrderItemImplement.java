package project.management.usersmanagement.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.management.usersmanagement.Repository.OrderItemsRepository;
import project.management.usersmanagement.entities.OrderItem;

import java.util.List;
@Service
public class OrderItemImplement implements IOrderItem {
    @Autowired
    private  OrderItemsRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);

    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);

    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return null;
    }
}
