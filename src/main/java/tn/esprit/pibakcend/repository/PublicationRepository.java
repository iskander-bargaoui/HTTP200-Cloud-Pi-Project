package tn.esprit.pibakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {
    /*List<Publication> findAllByOrderByDateCreationPubDesc();
    List<Publication> findAllByUserOrderByDateCreationPubDesc(User user);*/
    @Query("SELECT p FROM User u, Publication p WHERE (u.id = :idUser) AND (p.idPub = :idPub)")
    List<Publication> getPubHistory(@Param("idPub") Integer idPub,@Param("idUser") Long idUser);
}
