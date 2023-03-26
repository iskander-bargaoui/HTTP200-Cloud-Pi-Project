package tn.esprit.pibakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {
    /*List<Publication> findAllByOrderByDateCreationPubDesc();
    List<Publication> findAllByUserOrderByDateCreationPubDesc(User user);*/
}
