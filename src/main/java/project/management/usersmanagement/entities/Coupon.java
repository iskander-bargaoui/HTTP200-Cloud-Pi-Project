package project.management.usersmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Double discount;
    private Date expirationDate;
    private BigDecimal NewtotalPrice;


    public boolean isValid() {
        Date currentDate = new Date();
        return this.expirationDate.after(currentDate);
    }
    @ManyToOne
    private User user;


}
