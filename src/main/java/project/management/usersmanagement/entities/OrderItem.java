package project.management.usersmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@Data
@Entity(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "quantity")
    private  int quantity;

    @Column(name = "unit_price")
    private Double unitprice;


    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    //private Double price;
    public OrderItem(){}

    public OrderItem(Order order,  Product product,  int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.unitprice = price;
        this.order= order;
        this.createdDate = new Date();
    }
    public double getPrice() {

        return this.unitprice *this.quantity;
    }

    public void setPrice(double price) {
    }
/*    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return unitprice;
    }

    public void setPrice(double price) {
        this.unitprice = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }



    public Double getSubtotal() {
        return price * quantity;
    }*/
}
