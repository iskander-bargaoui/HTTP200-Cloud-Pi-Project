package tn.esprit.pibakcend.restController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.service.IUser;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserRestController {
    IUser iuser;
    @PutMapping("/toggleFavoritePublication/{idUser}/{idPub}")
    public User toggleFavoritePublication(@PathVariable ("idUser") Long idUser, @PathVariable("idPub") Integer idPub) {
       return iuser.toggleFavoritePublication(idUser, idPub);
    }
    @GetMapping("/getFavoritePublicationsByUserId/{idUser}")
    public List<Publication> getFavoritePublicationsByUserId(@PathVariable("idUser") Long idUser) {
        return iuser.getFavoritePublicationsByUserId(idUser);
    }
}
