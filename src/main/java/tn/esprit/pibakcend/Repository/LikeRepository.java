package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Integer> {

    Optional<Like> findByPublicationAndUser(Publication publication, User user);
    Optional<Like> findByCommentaireAndUser(Commentaire commentaire, User user);


}
