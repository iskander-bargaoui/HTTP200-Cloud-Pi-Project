package project.management.usersmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.management.usersmanagement.entities.Category;


import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
