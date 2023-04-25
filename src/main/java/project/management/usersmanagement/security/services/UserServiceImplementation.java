package project.management.usersmanagement.security.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import project.management.usersmanagement.Repository.UserRepository;
import project.management.usersmanagement.entities.ERole;
import project.management.usersmanagement.entities.Role;
import project.management.usersmanagement.repository.RoleRepository;
import project.management.usersmanagement.entities.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j

    public class UserServiceImplementation implements IUser, UserDetailsService {

    UserRepository userRepository;
    RoleRepository roleRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User retrieveUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> retrieveAllUsers() {
        List<User> l = userRepository.findAll();
        System.out.println(l.size());
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addRoleToUser(String username, ERole roleName) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);
        user.get().getRoles().add(role.get());
    }

    @Override
    public boolean ifEmailExist(String email) {
        return false;
    }

    @Override
    public User getUserByMail(String mail) {
        return null;
    }

    @Override
    public List<User> retrieveUserByAddress(String adressUser) {
        return null;
    }

    @Override
    @Scheduled(fixedDelay = 1000)

    public long retrieveUserByCount() {
        log.info("nb users : " + userRepository.count());
        return userRepository.count();
    }

    @Override
    public String updatePassword(String emailUser, String CurrentPassword, String confirmPassword) {
        Optional<User> u = userRepository.findByEmail(emailUser);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(CurrentPassword, u.get().getPassword())) {
            System.out.println("Pass Matches");
            String encodedPassword = passwordEncoder.encode(confirmPassword);
            u.get().setPassword(encodedPassword);
        } else {
            return "Password Doesn't Match";
        }
        userRepository.save(u.get());
        return "User Password Updated";
    }

    @Override
    public void forgotpass(String emailuser) {
        Optional<User> d = userRepository.findByEmail(emailuser);

        final String username = "http200pi@gmail.com";
        final String password = "kmeswtcspgqigwsh";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("http200pi@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailuser));
            message.setSubject("Reset Your Password");
            message.setText("This a non reply message from CareerTN\n " + "Dear Client \n"
                    + "Please click on the following link to reset your password: \n" + "http://localhost:8080/updatepassword/");

            Transport.send(message);

            log.info("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            log.error("user {} not found in the database", username);
            throw new UsernameNotFoundException("user not found");

        } else {
            log.info("user {} found in the database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(),
                authorities);
    }

    @Override
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userRepository.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
