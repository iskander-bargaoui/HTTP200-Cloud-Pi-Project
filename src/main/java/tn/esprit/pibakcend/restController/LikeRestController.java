package tn.esprit.pibakcend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.Repository.CommentaireRepository;
import tn.esprit.pibakcend.Repository.LikeRepository;
import tn.esprit.pibakcend.Repository.PublicationRepository;
import tn.esprit.pibakcend.Repository.UserRepository;
import tn.esprit.pibakcend.Service.ILike;
import tn.esprit.pibakcend.Service.LikeServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
public class LikeRestController {
    LikeRepository likeRepository;

    PublicationRepository publicationRepository;
    ILike iLike;


    @PostMapping("/ToggleLikesP/{idPub}/{idUser}")
    public Publication ToggleLikesP(@PathVariable("idPub") Integer idPub, @RequestParam LikeType likeType, @PathVariable("idUser") Long idUser) {
        return iLike.ToggleLikesP(idPub, likeType, idUser);

    }
    @PostMapping("/Comms/{idComm}/{idUser}")
    public Commentaire ToggleLikesC(@PathVariable("idComm") Integer idComm, @RequestParam LikeType likeType, @PathVariable("idUser") Long idUser) {
        return iLike.ToggleLikesC(idComm, likeType, idUser);

    }
}