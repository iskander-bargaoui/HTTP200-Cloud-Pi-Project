package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation,Integer> {
}
