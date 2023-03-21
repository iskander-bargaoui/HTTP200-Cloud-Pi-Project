package tn.esprit.pibakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Publication;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {
}
