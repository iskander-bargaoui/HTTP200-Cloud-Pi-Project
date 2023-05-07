package tn.esprit.pibakcend.RestController;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.security.services.INotification;
import tn.esprit.pibakcend.security.services.IReservation;
import tn.esprit.pibakcend.entities.Notification;
import tn.esprit.pibakcend.entities.Reservation;

import javax.mail.MessagingException;
import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "//localhost:4200")
@RequestMapping("/api")
@AllArgsConstructor

public class ReservationRestController {
    IReservation iReservation;
    INotification iNotification ;


    @PostMapping("/AddRes")

    public Reservation addRes(@RequestBody Reservation A) throws MessagingException {
        iReservation.sendHtmlEmail(A);
        return iReservation.addRes(A);
    }

    @PutMapping("/UpdateRes")
    public Reservation updateRes(@RequestBody Reservation A) {
        return iReservation.updateRes(A);
    }

    @GetMapping("/GetResByID/{id}")
    public Reservation retrieveNotById(@PathVariable("id") long id) throws AccessDeniedException {
        return iReservation.retrieveResById(id);
    }

    @GetMapping("/GetAllRes")

    public List<Reservation> retrieveAllRes()  { return iReservation.retrieveAllRes(); }

    @DeleteMapping("/DeleteRes/{id}")
    public void deleteRes(@PathVariable("id") long id) throws AccessDeniedException {
        iReservation.deleteRes(id);
    }

    @GetMapping("/Getremainder")
    @Scheduled(fixedRate = 30000L)
    public List<Reservation> findReservationsBefore24Hours() {
        List<Reservation> reservations = iReservation.findReservationsBefore24Hours();
        for (Reservation reservation : reservations) {
            Notification notification = new Notification() ;
            notification.setMessage("dont forgate that your reservation");
            notification.setNamen(reservation.getNameR());
            notification.setDateNotification(Date.from(Instant.now()));
            iNotification.addNot(notification);
            System.out.println("addnot");
        }
        return reservations;
    }





}

