package project.management.usersmanagement.dto;

import java.util.List;
import lombok.Data;

@Data
public class CreateOrderDto {
    private long userId;
    private List<OrderItemDto> orderItems;
}
