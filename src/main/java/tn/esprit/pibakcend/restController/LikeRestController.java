package tn.esprit.pibakcend.restController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.repository.CommentaireRepository;
import tn.esprit.pibakcend.repository.LikeRepository;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;
import tn.esprit.pibakcend.service.ILike;
import tn.esprit.pibakcend.service.LikeServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/likes")
@CrossOrigin("http://localhost:4200")
public class LikeRestController {
    LikeRepository likeRepository;

    PublicationRepository publicationRepository;
    ILike iLike;


    @PostMapping("/ToggleLikesP/{idPub}/{idUser}")
    public Publication ToggleLikesP(@PathVariable("idPub") Integer idPub, @RequestParam LikeType likeType, @PathVariable("idUser") Long idUser) {
        return iLike.ToggleLikesP(idPub, likeType, idUser);

    }
    @PostMapping("/ToggleLikesC/{idComm}/{idUser}")
    public Commentaire ToggleLikesC(@PathVariable("idComm") Integer idComm, @RequestParam LikeType likeType, @PathVariable("idUser") Long idUser) {
        return iLike.ToggleLikesC(idComm, likeType, idUser);

    }
}