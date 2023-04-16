package project.management.usersmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.management.usersmanagement.entities.Role;
import project.management.usersmanagement.entities.ERole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    // Find a Role by its name for an Admin
    Optional<Role> findByName(ERole roleAdmin);
}

