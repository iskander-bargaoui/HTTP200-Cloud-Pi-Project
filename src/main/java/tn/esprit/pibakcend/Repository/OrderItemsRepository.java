package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pibakcend.entities.OrderItem;

import java.util.List;
@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrderId(Long orderId);

}
