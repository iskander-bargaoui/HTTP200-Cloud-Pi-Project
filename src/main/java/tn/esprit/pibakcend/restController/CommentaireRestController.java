package tn.esprit.pibakcend.restController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.service.ICommentaire;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/commentaire")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentaireRestController {
    ICommentaire iCommentaire;


    @PutMapping("/UpdateCommentaire/{idComm}")
    Commentaire updateComm(@RequestBody Commentaire comm,@PathVariable Integer idComm){
        return iCommentaire.updateComm(comm,idComm);
    }

    @GetMapping("RetrieveCommentaireByPubId/{idPub}")
    List<Commentaire> retrieveCommentaireByPubId(@PathVariable ("idPub") Integer idPub){
    return iCommentaire.retrieveCommentaireByPubId(idPub);
    }

    @PostMapping("/AssignCommentaireToPub/{idPub}/{idUser}")
    Commentaire assignCommentaireToPub (@RequestBody Commentaire comm,@PathVariable ("idPub") Integer idPub,@PathVariable ("idUser") Long idUser){
    return iCommentaire.assignCommentaireToPub(comm,idPub,idUser);
    }

    @DeleteMapping("/DeleteCommentaire/{idComm}")
    void deleteCommentaire(@PathVariable Integer idComm){
    iCommentaire.deleteCommentaire(idComm);
    }
    @GetMapping("/CountCommentsInPublication/{idPub}/count")
    public Integer countCommentsInPublication(@PathVariable("idPub") Integer idPub) {
        return iCommentaire.countByPublicationId(idPub);
    }
}
