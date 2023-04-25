package project.management.usersmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponViewDto {
    private Long id;
    private String code;
    private BigDecimal discount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;
    private UserViewDto user;
    private BigDecimal NewtotalPrice;
    private OrderViewDto orderviewDto;
}
