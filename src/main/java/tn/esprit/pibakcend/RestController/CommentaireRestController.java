package tn.esprit.pibakcend.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.security.services.ICommentaire;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
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

    @PostMapping("/AssignCommentaireToPub/{idPub}")
    Commentaire assignCommentaireToPub (@RequestBody Commentaire comm,@PathVariable ("idPub") Integer idPub) throws IOException {
    return iCommentaire.assignCommentaireToPub(comm,idPub);
    }

    @DeleteMapping("/DeleteCommentaire/{idComm}")
    void deleteCommentaire(@PathVariable Integer idComm) throws AccessDeniedException {
    iCommentaire.deleteCommentaire(idComm);
    }
    @GetMapping("/CountCommentsInPublication/{idPub}/count")
    public Integer countCommentsInPublication(@PathVariable("idPub") Integer idPub) {
        return iCommentaire.countByPublicationId(idPub);
    }
}
