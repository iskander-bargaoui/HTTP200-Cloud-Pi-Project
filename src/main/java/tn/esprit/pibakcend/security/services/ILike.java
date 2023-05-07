package tn.esprit.pibakcend.security.services;

import javafx.scene.control.Toggle;
import tn.esprit.pibakcend.entities.*;

import java.util.List;

public interface ILike {
   /* //Like addLikeDislike(Like likeDislike);
    Like updateLikeDislike(Like likeDislike);
    Like retrieveLikeDislikeById(Integer id);
    List<Like> retrieveAllLikeDislike();
    void deleteLikeDislike(Integer id);

    Like addLikeToPublication(Integer idPub, Long idUser);
    Like addLikeToCommentaire(Integer idComm, Long idUser);
    Like addDisLikeToPublication(Integer idPub, Long idUser);
    Like addDisLikeToCommentaire(Integer idComm, Long idUser);

    public void removeLikeFromPublication(Integer idPub, Long idUser);
    public void removeLikeFromCommentaire(Integer idComm, Long idUser);

    Like findByPublicationAndUser(Publication publication, User user);
    Like findByCommentaireAndUser(Commentaire commentaire, User user);

    List<Like> retrieveAllLikesByPublicationId(long publicationId);
    List<Like> retrieveAllDislikesByPublicationId(long publicationId);
    List<Like> retrieveAllLikesByCommentaireId(long commentaireId);
    List<Like> retrieveAllDislikesByCommentaireId(long commentaireId);
    Like retrieveLikeDislikeByUserIdAndPublicationId(long userId, long publicationId);
    Like retrieveLikeDislikeByUserIdAndCommentaireId(long userId, long commentaireId);*/

    public Publication ToggleLikesP (Integer idPub , LikeType likeType );
    public Commentaire ToggleLikesC (Integer idComm , LikeType likeType );

}
