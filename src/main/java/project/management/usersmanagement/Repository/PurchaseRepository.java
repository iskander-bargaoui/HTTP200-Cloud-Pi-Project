package project.management.usersmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.management.usersmanagement.entities.Purchase;
import project.management.usersmanagement.entities.User;

import java.util.List;
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long>{
    List<Purchase> findByUser(User user);
}
