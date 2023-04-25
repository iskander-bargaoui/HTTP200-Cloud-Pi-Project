package project.management.usersmanagement.dto;

import lombok.Data;
import project.management.usersmanagement.entities.Order;
import project.management.usersmanagement.entities.OrderItem;
import project.management.usersmanagement.entities.User;

import java.util.Set;

@Data
public class Purchase {
    private User user;
    //private Address shippingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
