package tn.esprit.pibakcend.restController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.Commentaire;
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

    @GetMapping("/GetCommentaireByID/{id}")
    public Commentaire retrieveCommentaireById(@PathVariable("id") Integer id) {
        return iCommentaire.retrieveCommentaireById(id);
    }
    @GetMapping("/GetAllCommentaire")
    public List<Commentaire> retrieveAllCommentaire() {
        return iCommentaire.retrieveAllCommentaire();
    }

    @DeleteMapping("/DeleteCommentaire/{id}")
    public void deleteCommentaire(@PathVariable("id") Integer id) {
        iCommentaire.deleteCommentaire(id);
    }
}
