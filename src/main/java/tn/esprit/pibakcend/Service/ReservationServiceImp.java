package tn.esprit.pibakcend.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.ReservationRepository;
import tn.esprit.pibakcend.entities.Reservation;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImp implements IReservation{
ReservationRepository reservationRepository ;
    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(Reservation A) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("ghazi.griguich@esprit.tn"));
        message.setRecipients(MimeMessage.RecipientType.TO, "ghazi.griguich@esprit.tn");
        message.setSubject("Test email from Spring");

        String htmlContent = "<h1>date of reservation is </h1>" + A.dateReservetion ;
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    @Override
    public Reservation addRes(Reservation A) {

        return reservationRepository.save(A);
    }


    @Override
    public Reservation updateRes(Reservation A) {
        return reservationRepository.save(A);
    }

    @Override
    public Reservation retrieveResById(long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> retrieveAllRes() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteRes(long id) {
        reservationRepository.deleteById(id);
    }
}
