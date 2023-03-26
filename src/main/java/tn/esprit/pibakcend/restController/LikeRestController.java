package tn.esprit.pibakcend.restController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.repository.CommentaireRepository;
import tn.esprit.pibakcend.repository.LikeRepository;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;
import tn.esprit.pibakcend.service.LikeServiceImp;

@RestController
@AllArgsConstructor
public class LikeRestController {
    LikeRepository likeRepository;

    LikeServiceImp likeServiceImp;

    // Endpoint for liking a publication
    @PostMapping("/publications/{publicationId}/likes")
    public ResponseEntity<Like> addLikeToPublication(@PathVariable Integer publicationId, @RequestParam Long userId) {
        Like like = likeServiceImp.addLikeToPublication(publicationId, userId);
        return ResponseEntity.ok(like);
    }
    @PostMapping("/publications/{publicationId}/dislikes")
    public ResponseEntity<Like> addDislikeToPublication(@PathVariable Integer publicationId, @RequestParam Long userId) {
        Like dislike = likeServiceImp.addDisLikeToPublication(publicationId, userId);
        return ResponseEntity.ok(dislike);
    }

    @PostMapping("/commentaires/{commentaireId}/likes")
    public ResponseEntity<Like> addLikeToCommentaire(@PathVariable Integer commentaireId, @RequestParam Long userId) {
        Like like = likeServiceImp.addLikeToCommentaire(commentaireId, userId);
        return ResponseEntity.ok(like);
    }

    @PostMapping("/commentaires/{commentaireId}/dislikes")
    public ResponseEntity<Like> addDislikeToCommentaire(@PathVariable Integer commentaireId, @RequestParam Long userId) {
        Like dislike = likeServiceImp.addDisLikeToCommentaire(commentaireId, userId);
        return ResponseEntity.ok(dislike);
    }
}
