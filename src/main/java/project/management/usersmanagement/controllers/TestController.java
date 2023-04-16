package project.management.usersmanagement.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

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

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
