package project.management.usersmanagement.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.management.usersmanagement.Services.IUser;
import project.management.usersmanagement.entities.User;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j

public class UserRestController {

    @Autowired
    IUser iUser;

    @PostMapping("/addUser")
    public User addSkieur(@RequestBody User user){
        return iUser.addUser(user);
    }

    @PutMapping("/updateUser")
    public User updateSkieur(@RequestBody User user ){
        return iUser.updateUser(user);
    }

    @GetMapping("/getUserById/{id}")
    public User retrieveUserById(@PathVariable("id") Long id){
        return iUser.retrieveUserById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAllUsers")
    public List<User> retrieveAllSkieurs(){
        return iUser.retrieveAllUsers();
    }

    @DeleteMapping("/delUser/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        iUser.deleteUser(id);
    }

}
