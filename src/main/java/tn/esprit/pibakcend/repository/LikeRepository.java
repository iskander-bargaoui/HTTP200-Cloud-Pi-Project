package tn.esprit.pibakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Integer> {
   /*
    Like findByUserAndCommentaire(User user, Commentaire commentaire);
    List<Like> findAllByPublicationAndLikeStatusIsTrue(Publication publication);
    List<Like> findAllByPublicationAndDislikeStatusIsTrue(Publication publication);
    List<Like> findAllByCommentaireAndLikeStatusIsTrue(Commentaire commentaire);
    List<Like> findAllByCommentaireAndDislikeStatusIsTrue(Commentaire commentaire);

    Like findByCommentaireAndUser(Commentaire commentaire, User user);*/

    Optional<Like> findByPublicationAndUser(Publication publication, User user);


}
