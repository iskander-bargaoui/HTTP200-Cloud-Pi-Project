package tn.esprit.pibakcend.restController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.repository.CommentaireRepository;
import tn.esprit.pibakcend.repository.LikeRepository;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;
import tn.esprit.pibakcend.service.ILike;
import tn.esprit.pibakcend.service.LikeServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
public class LikeRestController {
   LikeRepository likeRepository;

   PublicationRepository publicationRepository;

    //LikeServiceImp likeServiceImp;

    ILike iLike;

    // Endpoint for liking a publication
    @PostMapping("/AddLikeToPublication/{idPub}")
    public ResponseEntity<Like> addLikeToPublication(@PathVariable Integer idPub, @RequestParam Long idUser) {
        Like like = iLike.addLikeToPublication(idPub, idUser);
        return ResponseEntity.ok(like);
    }
    @PostMapping("/AddDislikeToPublication/{idPub}")
    public ResponseEntity<Like> addDislikeToPublication(@PathVariable Integer idPub, @RequestParam Long idUser) {
        Like dislike = iLike.addDisLikeToPublication(idPub, idUser);
        return ResponseEntity.ok(dislike);
    }

    @PostMapping("/AddLikeToCommentaire/{idComm}")
    public ResponseEntity<Like> addLikeToCommentaire(@PathVariable Integer idComm, @RequestParam Long idUser) {
        Like like = iLike.addLikeToCommentaire(idComm, idUser);
        return ResponseEntity.ok(like);
    }

    @PostMapping("/AddDislikeToCommentaire/{idComm}")
    public ResponseEntity<Like> addDislikeToCommentaire(@PathVariable Integer idComm, @RequestParam Long idUser) {
        Like dislike = iLike.addDisLikeToCommentaire(idComm, idUser);
        return ResponseEntity.ok(dislike);
    }
    @DeleteMapping("/RemoveLikeFromPublication/{idPub}/{idUser}")
    public ResponseEntity<String> removeLikeFromPublication(@PathVariable Integer idPub, @PathVariable Long idUser) {
        iLike.removeLikeFromPublication(idPub, idUser);
        return ResponseEntity.ok("Like removed successfully");
    }
    @DeleteMapping("/RemoveLikeFromCommentaire/{idComm}/{idUser}")
    public ResponseEntity<String> removeLikeFromCommentaire(@PathVariable Integer idComm, @PathVariable Long idUser) {
        iLike.removeLikeFromCommentaire(idComm, idUser);
        return ResponseEntity.ok("Like removed successfully");
    }

    @GetMapping("/GetAllLikeDislike")
    public List<Like> retrieveAllLikeDislike() {
        return iLike.retrieveAllLikeDislike();
    }

}
