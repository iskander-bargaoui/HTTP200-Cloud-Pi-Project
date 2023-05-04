package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pibakcend.entities.Role;
import tn.esprit.pibakcend.entities.ERole;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    // Find a Role by its name for an Admin
    Optional<Role> findByName(ERole roleAdmin);
}

