package project.management.usersmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.management.usersmanagement.entities.User;
import project.management.usersmanagement.security.services.IUser;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

  @Autowired
  IUser iUser;

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
}
