package project.management.usersmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.management.usersmanagement.entities.EOrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private Double totalPrice;
    //private Double NewtotalPrice;
    private UserViewDto user;
    private List<OrderItemViewDto> orderItems;
    private String status;

}
