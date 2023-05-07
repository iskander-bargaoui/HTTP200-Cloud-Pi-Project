package tn.esprit.pibakcend.security.services;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.ReservationRepository;
import tn.esprit.pibakcend.Repository.UserRepository;
import tn.esprit.pibakcend.entities.Profile;
import tn.esprit.pibakcend.entities.Reservation;
import tn.esprit.pibakcend.entities.User;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImp implements IReservation{
ReservationRepository reservationRepository ;
    private JavaMailSender mailSender;
    UserRepository userRepository;



    @Override
    public void sendHtmlEmail(Reservation A) throws MessagingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String emailRecipient = user.getEmail(); // assuming the username is the email address
        System.out.println(emailRecipient);

        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("http200pi@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, emailRecipient);
        message.setSubject("Reservation");
        // forma du date de reservation
        Date date = A.dateReservetionR;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dateString = dateFormat.format(date);
        String htmlContent = "<h1>date of reservation is </h1>" + dateString ;
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    @Override
    public Reservation addRes(Reservation A) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        A.setUserrr(user);
        return reservationRepository.save(A);
    }


    @Override
    public Reservation updateRes(Reservation A) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        A.setUserrr(user);
        return reservationRepository.save(A);
    }

    @Override
    public Reservation retrieveResById(long id) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Reservation> profileOptional = reservationRepository.findById(id);
        if (profileOptional.isPresent()) {
            Reservation reservation = profileOptional.get();
            if (reservation.getUserrr().equals(user)) {
                return reservation ;
            } else {
                throw new AccessDeniedException("You do not have permission to access this profile");
            }
        } else {
            throw new EntityNotFoundException("Profile with id " + id + " not found");
        }
    }

    @Override
    public List<Reservation> retrieveAllRes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User userrr = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return reservationRepository.findAllByuserrr(userrr);
    }

    @Override
    public void deleteRes(long id) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Reservation> profileOptional = reservationRepository.findById(id);
        if (profileOptional.isPresent()) {
            Reservation reservation = profileOptional.get();
            if (reservation.getUserrr().equals(user)) {
                reservationRepository.deleteById(id);
            } else {
                throw new AccessDeniedException("You do not have permission to delete this event");
            }
        } else {
            throw new EntityNotFoundException("profile with id " + id + " not found");
        }
    }

    @Override
    public List<Reservation> findReservationsBefore24Hours () {
       /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        */
        return reservationRepository.findReservationsBefore24Hours() ; }
}

