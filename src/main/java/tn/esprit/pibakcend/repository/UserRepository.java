package tn.esprit.pibakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
