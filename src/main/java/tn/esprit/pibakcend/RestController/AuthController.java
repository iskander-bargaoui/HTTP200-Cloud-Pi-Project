package tn.esprit.pibakcend.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.management.usersmanagement.payload.request.LoginRequest;
import tn.esprit.pibakcend.Repository.ConfirmationTokenRepository;
import tn.esprit.pibakcend.Repository.RoleRepository;
import tn.esprit.pibakcend.Repository.UserRepository;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.payload.request.SignupRequest;
import tn.esprit.pibakcend.payload.response.JwtResponse;
import tn.esprit.pibakcend.payload.response.MessageResponse;
import tn.esprit.pibakcend.security.jwt.JwtUtils;
import tn.esprit.pibakcend.security.services.IUser;
import tn.esprit.pibakcend.security.services.UserDetailsImpl;

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
  private ConfirmationTokenRepository confirmationTokenRepository;

  @Autowired
  private EmailService emailService;

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

  // register
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    User existingUser = userRepository.findByEmail(signUpRequest.getEmail()).orElse(null);
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    } else {

      if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
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
      // Vérification par mail
      ConfirmationToken confirmationToken = new ConfirmationToken(user);

      confirmationTokenRepository.save(confirmationToken);

      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setTo(user.getEmail());
      mailMessage.setSubject("Complete Registration!");
      mailMessage.setFrom("http200pi@gmail.com");
      mailMessage.setText("To confirm your account, please click here : "
              +"http://localhost:8080/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

      emailService.sendEmail(mailMessage);
      return ResponseEntity.ok(new MessageResponse("Please verify your email!")); // User registered successfuly
    }
  }


  // confirm mail
  // api/auth/confirm-account
  @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken)
  {
    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

    if(token != null)
    {
      User user = userRepository.findByEmail(token.getUserEntity().getEmail()).get();
      user.setEnabled(true);
      userRepository.save(user);
      return ResponseEntity.ok(new MessageResponse("Account verified! User registered successfully!"));

    }
    else
    {
      return ResponseEntity.ok(new MessageResponse("Error: Invalid Link!"));

    }
  }
}
