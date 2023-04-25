package tn.esprit.pibakcend.dto;

import lombok.Data;
import tn.esprit.pibakcend.entities.*;


import java.util.Set;

@Data
public class Purchase {
    private User user;
    private Order order;
    private Set<OrderItem> orderItems;
}
