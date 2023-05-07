package tn.esprit.pibakcend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.Repository.CommentaireRepository;
import tn.esprit.pibakcend.Repository.LikeRepository;
import tn.esprit.pibakcend.Repository.PublicationRepository;
import tn.esprit.pibakcend.Repository.UserRepository;
import tn.esprit.pibakcend.security.services.ILike;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/likes")
@CrossOrigin("http://localhost:4200")
public class LikeRestController {
    LikeRepository likeRepository;

    PublicationRepository publicationRepository;
    ILike iLike;


    @PostMapping("/ToggleLikesP/{idPub}")
    public Publication ToggleLikesP(@PathVariable("idPub") Integer idPub, @RequestParam LikeType likeType) {
        return iLike.ToggleLikesP(idPub, likeType);

    }
    @PostMapping("/ToggleLikesC/{idComm}")
    public Commentaire ToggleLikesC(@PathVariable("idComm") Integer idComm, @RequestParam LikeType likeType) {
        return iLike.ToggleLikesC(idComm, likeType);

    }
}