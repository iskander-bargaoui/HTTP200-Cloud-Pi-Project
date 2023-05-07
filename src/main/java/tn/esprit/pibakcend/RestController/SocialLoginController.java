package tn.esprit.pibakcend.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.payload.request.JwtLogin;
import tn.esprit.pibakcend.payload.response.LoginResponse;
import tn.esprit.pibakcend.payload.response.TokenDto;
import tn.esprit.pibakcend.Repository.UserRepository;
import tn.esprit.pibakcend.security.jwt.JwtUtils;
import tn.esprit.pibakcend.security.services.IUser;

import java.io.IOException;
import java.util.Collections;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class SocialLoginController {

    @Autowired
    IUser iUser; //Iuser UserService
    @Autowired
    UserRepository userRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder encoder;

    @Value("${google.id}")
    private String idClient;

    @Value("${secretPsw}")
    String secretPsw;

    int n = 5;



    @PostMapping("/google")
    public LoginResponse loginWithGoogle(@RequestBody TokenDto tokenDto) throws IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder ver = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singleton(idClient));
        log.info("googleIdTokeen" + tokenDto.getValue());
        GoogleIdToken googleIdToken = GoogleIdToken.parse(ver.getJsonFactory(), tokenDto.getValue());
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        String username = payload.get("given_name").toString().concat("-").toString().concat(getAlphaNumericString(n));
        String firstName = payload.get("given_name").toString();
        String lastName = payload.get("family_name").toString();
        // return new ResponseEntity<>(payload, HttpStatus.OK);

        return login(payload.getEmail(), username,firstName,lastName);
    }




    static String getAlphaNumericString(int n) {

        String randomNumber = "0123456789";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (randomNumber.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(randomNumber.charAt(index));
        }

        return sb.toString();
    }

    //
    @PostMapping("/facebook")
    public LoginResponse loginWithFacebook(@RequestBody TokenDto tokenDto) {
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        String[] data = { "email", "name", "picture", "first_name", "last_name", };
        User    userFacebook = facebook.fetchObject("me", User.class, data);
        // String


        String username = userFacebook.getFirstName().concat(getAlphaNumericString(n));

        return login(userFacebook.getEmail(), username,userFacebook.getFirstName(),userFacebook.getLastName());
    }

    //
    private LoginResponse login(String email, String username, String prenom ,String nom) {
        boolean result = iUser.ifEmailExist(email); // t // f
        if (!result) {
            tn.esprit.pibakcend.entities.User user = new tn.esprit.pibakcend.entities.User();
            user.setEmail(email);
            user.setPassword(encoder.encode("root1234"));
            user.setUsername(username);
            user.setPrenom(prenom);
            user.setNom(nom);

            userRepo.save(user);
        }
        JwtLogin jwtLogin = new JwtLogin();
        jwtLogin.setUsername(username);
        jwtLogin.setPassword("root1234");
        jwtLogin.setNom(nom);
        jwtLogin.setPrenom(prenom);

        log.info("jwt:" + jwtLogin.getUsername());
        log.info("jwt:" + jwtLogin.getPassword());

        return jwtUtils.login(jwtLogin);
    }
}