package tn.esprit.pibakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Activity;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    List<Activity> findByUserIdOrderByActivityTimeDesc(Long userId);

}
