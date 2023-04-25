package project.management.usersmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.management.usersmanagement.Repository.UserRepository;
import project.management.usersmanagement.entities.ERole;
import project.management.usersmanagement.entities.Role;
import project.management.usersmanagement.entities.User;
import project.management.usersmanagement.payload.request.LoginRequest;
import project.management.usersmanagement.payload.request.SignupRequest;
import project.management.usersmanagement.payload.response.JwtResponse;
import project.management.usersmanagement.payload.response.MessageResponse;
import project.management.usersmanagement.repository.RoleRepository;
import project.management.usersmanagement.security.jwt.JwtUtils;
import project.management.usersmanagement.security.services.IUser;
import project.management.usersmanagement.security.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  IUser iUser;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    // Les paramétres de Connexion

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getUser().getId(),
                         userDetails.getUsername(),
                         userDetails.getUser().getEmail(),
                         roles,
                         userDetails.getUser().getNom(),
                         userDetails.getUser().getPrenom(),
                         userDetails.getUser().getPhoneNumber()));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    // Parameters for registration

    User user = new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getAddress(),
            signUpRequest.getPhoneNumber(),
            signUpRequest.getNom(),
            signUpRequest.getPrenom(),
            signUpRequest.getBirthDate());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    // Checks for created roles if found or not
    if (strRoles == null) { // ROLE_USER
      Role userRole = roleRepository.findByName(ERole.ROLE_GUEST)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          // Role Admin
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          // Role Worker
          break;
        case "travailleur":  // ROLE_MODERATOR
          Role modRole = roleRepository.findByName(ERole.ROLE_WORKER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          // ROLE Entreprise
          case "entreprise":  // ROLE_MODERATOR
            Role entreRole = roleRepository.findByName(ERole.ROLE_ENTREPRISE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(entreRole);
          break;

          // Role Par défaut = > Guest
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_GUEST)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }


    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
