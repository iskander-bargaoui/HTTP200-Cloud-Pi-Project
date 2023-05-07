package tn.esprit.pibakcend.RestController;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.payload.request.LoginRequest;
import tn.esprit.pibakcend.payload.request.PasswordRequest;
import tn.esprit.pibakcend.payload.request.SignupRequest;
import tn.esprit.pibakcend.payload.response.JwtResponse;
import tn.esprit.pibakcend.payload.response.MessageResponse;
import tn.esprit.pibakcend.Repository.ConfirmationTokenRepository;
import tn.esprit.pibakcend.Repository.RoleRepository;
import tn.esprit.pibakcend.Repository.UserRepository;
import tn.esprit.pibakcend.security.jwt.JwtUtils;
import tn.esprit.pibakcend.security.services.IUser;
import tn.esprit.pibakcend.security.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

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
                         userDetails.getUser().getPhoneNumber(),
                          userDetails.getUser().getBirthDate()));

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
        Role userRole = roleRepository.findByName(ERole.ROLE_WORKER) // Default Role
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
            case "guest":  // ROLE_MODERATOR
              Role guestRole = roleRepository.findByName(ERole.ROLE_GUEST)
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(guestRole);

              // ROLE Entreprise
            case "entreprise":  // ROLE_MODERATOR
              Role entreRole = roleRepository.findByName(ERole.ROLE_ENTREPRISE)
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(entreRole);
              break;
            // Role Par défaut = > Guest // Worker
            default:
              Role workerRole = roleRepository.findByName(ERole.ROLE_WORKER) // ROLE_GUEST
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(workerRole);
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
//               +"http://localhost:8080/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
      emailService.sendEmail(mailMessage);
      return ResponseEntity.ok(new MessageResponse("Please verify your email!")); // User registered successfuly

      // TO ADD ENABLE USER ONLY IF ACCOUNT ACTIVATED IN REGISTER METHOD .isEnabled()?
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

  // Session
  @GetMapping("/user")
  public Long getCurrentUser() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    Optional<User> userOptional = userRepository.findByUsername(username);
    User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return user.getId(); // User.getId() // getName()

  }
// logout
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new MessageResponse("You've been signed out!"));
  }
  // Cruds

  //@PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/addUser")
  public User addUser(@RequestBody User user){ return iUser.addUser(user); }

  @PutMapping("/updateUser")
  public User updateUser(@RequestBody User user ){
    return iUser.updateUser(user);
  }

 // @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping("/getUserById/{id}")
  public User retrieveUserById(@PathVariable("id") Long id){
    return iUser.retrieveUserById(id);
  }

  //@PreAuthorize("hasRole('ROLE_ADMIN')")
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

  @PutMapping("/enable/{id}")
  public ResponseEntity<User> enableUser(@PathVariable Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    user.setEnabled(true);
    User enabledUser = userRepository.save(user);
    return ResponseEntity.ok(enabledUser);
  }
  @PutMapping("/disable/{id}")
  public ResponseEntity<User> disableUser(@PathVariable Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    user.setEnabled(false);
    User enabledUser = userRepository.save(user);
    return ResponseEntity.ok(enabledUser);
  }
}
