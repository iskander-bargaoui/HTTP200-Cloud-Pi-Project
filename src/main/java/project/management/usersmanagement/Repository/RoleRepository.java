package project.management.usersmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.management.usersmanagement.entities.Role;
import project.management.usersmanagement.entities.RoleEnum;

public interface RoleRepository extends JpaRepository<Role,Long> {

    // Find a Role by its name for an Admin
    Role findByName(RoleEnum roleAdmin);
}

