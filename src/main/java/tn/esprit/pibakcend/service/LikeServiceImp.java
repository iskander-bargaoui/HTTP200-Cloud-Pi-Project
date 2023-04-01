package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.repository.CommentaireRepository;
import tn.esprit.pibakcend.repository.LikeRepository;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class LikeServiceImp implements  ILike{
    LikeRepository likeRepository;

    UserRepository userRepository;

    PublicationRepository publicationRepository;

    CommentaireRepository commentaireRepository;

    @Override
    public Like updateLikeDislike(Like likeDislike) {
        return likeRepository.save(likeDislike);
    }

    @Override
    public Like retrieveLikeDislikeById(Integer id) {
        return likeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Like> retrieveAllLikeDislike() {
        return likeRepository.findAll();
    }

    @Override
    public void deleteLikeDislike(Integer id) {
        likeRepository.deleteById(id);
    }

    @Override
    public Like addLikeToPublication(Integer idPub, Long idUser) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);;
        User user = userRepository.findById(idUser).orElse(null);;
        Like likeDislike = new Like(true, false, user, publication, null);
        return likeRepository.save(likeDislike);
    }

    @Override
    public Like addLikeToCommentaire(Integer idComm, Long idUser) {
        Commentaire commentaire = commentaireRepository.findById(idComm).orElse(null);;
        User user = userRepository.findById(idUser).orElse(null);;
        Like likeDislike = new Like(true, false, user, null, commentaire);
        return likeRepository.save(likeDislike);
    }

    @Override
    public Like addDisLikeToPublication(Integer idPub, Long idUser) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);;
        User user = userRepository.findById(idUser).orElse(null);;
        Like likeDislike = new Like(false, true, user, publication, null);
        return likeRepository.save(likeDislike);
    }

    @Override
    public Like addDisLikeToCommentaire(Integer idComm, Long idUser) {
        Commentaire commentaire = commentaireRepository.findById(idComm).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);;
        Like likeDislike = new Like(false, true, user, null, commentaire);
        return likeRepository.save(likeDislike);
    }

    @Override
    public void removeLikeFromPublication(Integer idPub, Long idUser) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        if (publication != null && user != null) {
            Like like = likeRepository.findByPublicationAndUser(publication, user);
            if (like != null) {
                likeRepository.delete(like);
            }
        }
    }

    @Override
    public void removeLikeFromCommentaire(Integer idComm, Long idUser) {
        Commentaire commentaire =commentaireRepository.findById(idComm).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        if (commentaire != null && user != null) {
            Like like = likeRepository.findByCommentaireAndUser(commentaire, user);
            if (like != null) {
                likeRepository.delete(like);
            }
        }
    }

    @Override
    public Like findByPublicationAndUser(Publication publication, User user) {
        return likeRepository.findByPublicationAndUser(publication, user);
    }

    @Override
    public Like findByCommentaireAndUser(Commentaire commentaire, User user) {
        return likeRepository.findByCommentaireAndUser(commentaire, user);    }
}
