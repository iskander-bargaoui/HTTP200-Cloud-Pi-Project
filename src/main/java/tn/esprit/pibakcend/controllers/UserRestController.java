package tn.esprit.pibakcend.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.payload.request.PasswordRequest;
import tn.esprit.pibakcend.Service.IUser;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserRestController {
    IUser iUser;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){ return iUser.addUser(user); }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user ){
        return iUser.updateUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getUserById/{id}")
    public User retrieveUserById(@PathVariable("id") Long id){
        return iUser.retrieveUserById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/allUsers")
    public List<User> retrieveAllUsers() {
        return iUser.retrieveAllUsers();

    }

    @DeleteMapping("/delUser/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        iUser.deleteUser(id);
    }

    @PutMapping("/updatepassword/{emailUser}")
    String updatePassword(@PathVariable("emailUser") String emailUser, @RequestBody PasswordRequest Password) {
      return  iUser.updatePassword(emailUser, Password.getCurrentPassword(), Password.getNewPassword());
    }

    @GetMapping("/sendme/{emailUser}")
    public void forgotpass(@PathVariable("emailUser") String emailUser) {
        iUser.forgotpass(emailUser);
    }

    @PutMapping("/toggleFavoritePublication/{idUser}/{idPub}")
    public User toggleFavoritePublication(@PathVariable ("idUser") Long idUser, @PathVariable("idPub") Integer idPub) {
        return iUser.toggleFavoritePublication(idUser, idPub);
    }
    @GetMapping("/getFavoritePublicationsByUserId/{idUser}")
    public List<Publication> getFavoritePublicationsByUserId(@PathVariable("idUser") Long idUser) {
        return iUser.getFavoritePublicationsByUserId(idUser);
    }
}
