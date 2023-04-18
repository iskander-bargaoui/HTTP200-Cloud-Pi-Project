package tn.esprit.pibakcend.restController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.Activity;
import tn.esprit.pibakcend.entities.ActivityType;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.repository.ActivityRepository;
import tn.esprit.pibakcend.service.IActivity;
import tn.esprit.pibakcend.service.IPublication;

import java.util.List;

@RestController
@AllArgsConstructor

public class PublicationRestController {
    IPublication iPublication;

    IActivity iActivity;

    //ActivityRepository activityRepository;

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
/*
    @PostMapping("/publications")
    public ResponseEntity<?> createPublication(@RequestBody Publication publication, @RequestParam Long userId) {
        Publication createdPublication = iPublication.addPub(publication, userId);
        iActivity.logActivity(createdPublication.getUser(), ActivityType.CREATE_PUBLICATION, createdPublication, null);
        return ResponseEntity.ok(createdPublication);
    }

    @GetMapping("/users/{userId}/activity")
    public ResponseEntity<?> getUserActivity(@PathVariable Long userId) {
        List<Activity> userActivity = activityRepository.findByUserIdOrderByActivityTimeDesc(userId);
        return ResponseEntity.ok(userActivity);
    }*/

}
