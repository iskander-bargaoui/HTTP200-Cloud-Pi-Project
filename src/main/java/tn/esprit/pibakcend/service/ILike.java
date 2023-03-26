package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.Like;

import java.util.List;

public interface ILike {
    Like addLikeDislike(Like likeDislike);
    Like updateLikeDislike(Like likeDislike);
    Like retrieveLikeDislikeById(Integer id);
    List<Like> retrieveAllLikeDislike();
    void deleteLikeDislike(Integer id);

    Like addLikeToPublication(Integer publicationId, Long userId);
    Like addLikeToCommentaire(Integer commentaireId, Long userId);
    Like addDisLikeToPublication(Integer publicationId, Long userId);
    Like addDisLikeToCommentaire(Integer commentaireId, Long userId);

    /*List<Like> retrieveAllLikesByPublicationId(long publicationId);
    List<Like> retrieveAllDislikesByPublicationId(long publicationId);
    List<Like> retrieveAllLikesByCommentaireId(long commentaireId);
    List<Like> retrieveAllDislikesByCommentaireId(long commentaireId);
    Like retrieveLikeDislikeByUserIdAndPublicationId(long userId, long publicationId);
    Like retrieveLikeDislikeByUserIdAndCommentaireId(long userId, long commentaireId);*/
}
