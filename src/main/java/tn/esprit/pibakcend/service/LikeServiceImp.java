package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.repository.CommentaireRepository;
import tn.esprit.pibakcend.repository.LikeRepository;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeServiceImp implements  ILike{
    LikeRepository likeRepository;

    UserRepository userRepository;

    PublicationRepository publicationRepository;

    CommentaireRepository commentaireRepository;

    @Override

    public Publication ToggleLikesP(Integer idPub, LikeType likeType, Long idUser) {
        Publication publication = publicationRepository.findById(idPub)
                .orElseThrow(() -> new IllegalArgumentException("Publication Not Found with ID - " + idPub));
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found with ID - " + idUser));

        // Retrieve existing like for the given publication and user (if any)
        Optional<Like> likeOptional = likeRepository.findByPublicationAndUser(publication, user);

        if (likeOptional.isPresent()) { // Existing like found
            Like existingLike = likeOptional.get();

            if (existingLike.getLikeType().equals(likeType)) {
                // Delete existing like
                likeRepository.delete(existingLike);
                if (likeType.equals(LikeType.LIKE)) {
                    publication.setLikeCount(publication.getLikeCount() - 1);
                } else {
                    publication.setDislikeCount(publication.getDislikeCount() - 1);
                }

            } else {
                existingLike.setLikeType(likeType);
                likeRepository.save(existingLike);
                if (likeType.equals(LikeType.LIKE)) {
                    publication.setLikeCount(publication.getLikeCount() + 1);
                    publication.setDislikeCount(publication.getDislikeCount() - 1);
                } else {
                    publication.setLikeCount(publication.getLikeCount() - 1);
                    publication.setDislikeCount(publication.getDislikeCount() + 1);
                }
            }
        } else { // No existing like found
            // Create new like and save it to the database
            Like newLike = mapToLike(publication, likeType, user);
            likeRepository.save(newLike);

            // Update publication's like count
            if (likeType.equals(LikeType.LIKE)) {
                publication.setLikeCount(publication.getLikeCount() + 1);
            } else {
                publication.setDislikeCount(publication.getDislikeCount() + 1);
            }
        }

        publicationRepository.save(publication);
        return publication;
    }

    private Like mapToLike(Publication publication, LikeType likeType, User user) {
        return Like.builder()
                .likeType(likeType)
                .publication(publication)
                .user(user)
                .build();
    }
    }




  /*  @Override
    public Publication ToggleLikes(Integer idPub, Long idUser) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        if (publication != null && user != null) {
            List<Like> Likes = likeRepository.findAll().stream()
                    .filter(x -> x.getPublication().getIdPub() == idPub)
                    .filter(x -> x.getUser().getId() == idUser)
                    .collect(Collectors.toList());
            if (Likes.size() > 0) {
                likeRepository.deleteById(Likes.get(0).getIdlike());
            }
            else {
                Like like = new Like();
                like.setUser(user);
                like.setPublication(publication);
                likeRepository.save(like);
            }
             }
            return publication;
        }*/
