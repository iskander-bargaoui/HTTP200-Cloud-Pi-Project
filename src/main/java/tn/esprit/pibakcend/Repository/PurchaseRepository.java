package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pibakcend.entities.Purchase;
import tn.esprit.pibakcend.entities.User;

import java.util.List;
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long>{
    List<Purchase> findByUser(User user);
}
