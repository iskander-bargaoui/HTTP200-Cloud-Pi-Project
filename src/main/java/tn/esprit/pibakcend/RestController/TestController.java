package tn.esprit.pibakcend.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.security.services.IUser;
import tn.esprit.pibakcend.security.services.UserServiceImplementation;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class TestController {

  @Autowired
  IUser iUser;
  UserServiceImplementation userServiceImplementation;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/guest")
  @PreAuthorize("hasRole('ROLE_GUEST') or hasRole('ROLE_WORKER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ENTREPRISE')")
  public String userAccess() {
    return "Guest Content, Available for all.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('ROLE_WORKER')")
  public String moderatorAccess() {
    return "Worker Board.";
  }

  @GetMapping("/getAllUsers")
  public List<User> retrieveAllUsers(){ return iUser.retrieveAllUsers();
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

  // enabled and disable user
  @PutMapping("/{id}/enable")
  public ResponseEntity<?> enableUser(@PathVariable Long id) {
    User user = userServiceImplementation.enableUser(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
  }

  @PutMapping("/{id}/disable")
  public ResponseEntity<?> disableUser(@PathVariable Long id) {
    User user = userServiceImplementation.disableUser(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
  }


}
