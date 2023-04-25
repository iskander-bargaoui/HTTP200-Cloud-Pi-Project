package project.management.usersmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.management.usersmanagement.entities.OrderItem;
@Repository
public interface OrderItemRep extends JpaRepository <OrderItem,Long>{
}
