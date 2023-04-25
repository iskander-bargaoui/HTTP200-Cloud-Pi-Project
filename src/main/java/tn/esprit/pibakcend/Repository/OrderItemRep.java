package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pibakcend.entities.OrderItem;
@Repository
public interface OrderItemRep extends JpaRepository <OrderItem,Long>{
}
