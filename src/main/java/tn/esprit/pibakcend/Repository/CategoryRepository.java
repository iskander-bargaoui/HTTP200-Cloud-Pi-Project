package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pibakcend.entities.Category;


import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
