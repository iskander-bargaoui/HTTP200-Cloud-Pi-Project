package project.management.usersmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.management.usersmanagement.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
