package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Publication;

import java.time.LocalDateTime;
import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {
    /*List<Publication> findAllByOrderByDateCreationPubDesc();
    List<Publication> findAllByUserOrderByDateCreationPubDesc(User user);*/
    List<Publication> findByIsFavoriteTrueAndFavoriteDateBefore(LocalDateTime date);

}
