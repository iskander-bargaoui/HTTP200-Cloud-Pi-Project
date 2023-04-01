package tn.esprit.pibakcend.restController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.service.IPublication;

import java.util.List;

@RestController
@AllArgsConstructor

public class PublicationRestController {
    IPublication iPublication;
    @PostMapping("/AddPublication/{idUser}")
    public Publication addPub(@RequestBody Publication pub,@PathVariable("idUser") Long idUser) {
        return iPublication.addPub(pub,idUser);
    }

    @PutMapping("/UpdatePublication/{idPub}")
    public Publication updatePub(@RequestBody Publication pub, @PathVariable ("idPub") Integer idPub) {
        return iPublication.updatePub(pub,idPub);
    }

    @GetMapping("/GetPublicationByID/{idPub}")
    public Publication retrievePublicationById(@PathVariable("idPub") Integer idPub) {
        return iPublication.retrievePublicationById(idPub);
    }
    @GetMapping("/GetAllPublication")
    public List<Publication> retrieveAllPublication() {
        return iPublication.retrieveAllPublication();
    }

    @DeleteMapping("/DeletePublication/{idPub}")
    public void deletePublication(@PathVariable("idPub") Integer idPub) {
        iPublication.deletePublication(idPub);
    }

    @GetMapping("/RetrievePublicationUserById/{idUser}")
    public List<Publication> retrievePublicationUserById (@PathVariable ("idUser") Long idUser){
    return iPublication.retrievePublicationUserById(idUser);
    }

    /*@PostMapping("/AssignPublicationToUser/{idPub}/{idUser}")
    public Publication assignPublicationToUser(@PathVariable("idPub") Integer idPub,@PathVariable("idUser") Long idUser) {
        return iPublication.assignPublicationToUser(idPub,idUser);
    }*/
}
