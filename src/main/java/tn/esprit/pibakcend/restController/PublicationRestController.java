package tn.esprit.pibakcend.restController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.service.IPublication;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/publications")
@CrossOrigin("http://localhost:4200")
public class PublicationRestController {
    IPublication iPublication;


    //ActivityRepository activityRepository;

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imagePath = iPublication.saveImage(file);
            return ResponseEntity.ok().body("Image uploaded successfully. Image Path : " + imagePath);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
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

    @PutMapping("/toggleFavoritePublication/{idUser}/{idPub}")
    public Publication toggleFavoritePublication(@PathVariable ("idUser") Long idUser, @PathVariable("idPub") Integer idPub) {
        return iPublication.toggleFavoritePublication(idUser, idPub);
    }
    @GetMapping("/getFavoritePublicationsByUserId/{idUser}")
    public List<Publication> getFavoritePublicationsByUserId(@PathVariable("idUser") Long idUser) {
        return iPublication.getFavoritePublicationsByUserId(idUser);
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
