package tn.esprit.pibakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire,Integer> {
  /*  List<Commentaire> findAllByPublicationOrderByDateCreationCommDesc(Publication publication);
    List<Commentaire> findAllByUserOrderByDateCreationCommDesc(User user);*/
}
