package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire,Integer> {
  /*  List<Commentaire> findAllByPublicationOrderByDateCreationCommDesc(Publication publication);
    List<Commentaire> findAllByUserOrderByDateCreationCommDesc(User user);*/

   /* @Query("SELECT COUNT(comm) FROM Commentaire comm")
    Integer countAllComments(Integer idPub);*/

    @Query("SELECT COUNT(comm) FROM Commentaire comm WHERE comm.publication.idPub = :idPub")
    Integer countByPublicationId(@Param("idPub") Integer idPub);
}
