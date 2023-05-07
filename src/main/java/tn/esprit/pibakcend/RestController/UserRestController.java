package tn.esprit.pibakcend.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.EmailService;
import tn.esprit.pibakcend.payload.request.PasswordRequest;
import tn.esprit.pibakcend.Repository.ConfirmationTokenRepository;
import tn.esprit.pibakcend.security.services.IUser;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:64657", maxAge = 3600, allowCredentials="true")

public class UserRestController {

    @Autowired
    IUser iUser;
    @Autowired
    EmailService emailService;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

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
}
