package tn.esprit.pibakcend.restController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.service.ICommentaire;

import java.util.List;
@RestController
@AllArgsConstructor
public class CommentaireRestController {
    ICommentaire iCommentaire;

    @PostMapping("/AddCommentaire")
    public Commentaire addComm(@RequestBody Commentaire Comm) {
        return iCommentaire.addComm(Comm);
    }

    @PutMapping("/UpdateCommentaire")
    public Commentaire updateComm(@RequestBody Commentaire Comm) {
        return iCommentaire.updateComm(Comm);
    }

    @GetMapping("/GetCommentaireByID/{idComm}")
    public Commentaire retrieveCommentaireById(@PathVariable("idComm") Integer idComm) {
        return iCommentaire.retrieveCommentaireById(idComm);
    }
    @GetMapping("/GetAllCommentaire")
    public List<Commentaire> retrieveAllCommentaire() {
        return iCommentaire.retrieveAllCommentaire();
    }

    @DeleteMapping("/DeleteCommentaire/{id}")
    public void deleteCommentaire(@PathVariable("id") Integer id) {
        iCommentaire.deleteCommentaire(id);
    }

    @PostMapping("/AssignCommentaireToUser/{idComm}/{idUser}")
    public Commentaire assignCommentaireToUser(@PathVariable("idComm") Integer idComm, @PathVariable("idUser") Long idUser) {
        return iCommentaire.assignCommentaireToUser(idComm,idUser);
    }
    @PostMapping("/AssignCommentaireToPub/{idComm}/{idPub}")
    public Commentaire assignCommentaireToPub(@PathVariable("idComm") Integer idComm, @PathVariable("idPub") Integer idPub) {
        return iCommentaire.assignCommentaireToPub(idComm,idPub);
    }
    @GetMapping("/CountCommentsInPublication/{idPub}/count")
    public Integer countCommentsInPublication(@PathVariable("idPub") Integer idPub) {
        return iCommentaire.countByPublicationId(idPub);
    }

    @GetMapping("/RetrieveCommentaireUserById/{idUser}")
    public List<Commentaire> retrieveCommentaireUserById (@PathVariable ("idUser") Long idUser){
        return iCommentaire.retrieveCommentaireUserById(idUser);
    }
}
