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
    public Like addLikeDislike(Like likeDislike) {
        return likeRepository.save(likeDislike);
    }

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
    public Like addLikeToPublication(Integer publicationId, Long userId) {
        Publication publication = publicationRepository.findById(publicationId).orElseThrow(() -> new NotFoundException("Publication not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Like likeDislike = new Like(true, false, user, publication, null);
        return likeRepository.save(likeDislike);
    }

    @Override
    public Like addLikeToCommentaire(Integer commentaireId, Long userId) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId).orElseThrow(() -> new NotFoundException("Commentaire not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Like likeDislike = new Like(true, false, user, null, commentaire);
        return likeRepository.save(likeDislike);
    }

    @Override
    public Like addDisLikeToPublication(Integer publicationId, Long userId) {
        Publication publication = publicationRepository.findById(publicationId).orElseThrow(() -> new NotFoundException("Publication not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Like likeDislike = new Like(false, true, user, publication, null);
        return likeRepository.save(likeDislike);
    }

    @Override
    public Like addDisLikeToCommentaire(Integer commentaireId, Long userId) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId).orElseThrow(() -> new NotFoundException("Commentaire not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Like likeDislike = new Like(false, true, user, null, commentaire);
        return likeRepository.save(likeDislike);
    }
}
