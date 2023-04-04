package project.management.usersmanagement.Config;

import org.springframework.data.jpa.repository.JpaRepository;
import project.management.usersmanagement.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findByAddress(String address);

}

