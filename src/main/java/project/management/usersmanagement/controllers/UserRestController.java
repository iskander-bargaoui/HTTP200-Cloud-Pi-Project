package project.management.usersmanagement.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.management.usersmanagement.payload.request.PasswordRequest;
import project.management.usersmanagement.security.services.IUser;
import project.management.usersmanagement.entities.User;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
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
}
