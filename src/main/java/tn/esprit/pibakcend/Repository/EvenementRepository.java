package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement,Integer> {
}
