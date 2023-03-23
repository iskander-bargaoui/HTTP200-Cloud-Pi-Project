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
    @PostMapping("/AddPublication")
    public Publication addPub(@RequestBody Publication pub) {
        return iPublication.addPub(pub);
    }

    @PutMapping("/UpdatePublication")
    public Publication updatePub(@RequestBody Publication pub) {
        return iPublication.updatePub(pub);
    }

    @GetMapping("/GetPublicationByID/{id}")
    public Publication retrievePublicationById(@PathVariable("id") Integer id) {
        return iPublication.retrievePublicationById(id);
    }

    @GetMapping("/GetAllPublication")
    public List<Publication> retrieveAllPublication() {
        return iPublication.retrieveAllPublication();
    }

    @DeleteMapping("/DeletePublication/{id}")
    public void deletePublication(@PathVariable("id") Integer id) {
        iPublication.deletePublication(id);
    }
}
