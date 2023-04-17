package project.management.usersmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.management.usersmanagement.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username); // User findByUsername
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findByAddress(String address);

}

