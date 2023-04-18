package tn.esprit.pibakcend.RestController;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.INotification;
import tn.esprit.pibakcend.Service.IReservation;
import tn.esprit.pibakcend.entities.Notification;
import tn.esprit.pibakcend.entities.Reservation;

import javax.mail.MessagingException;
import java.util.List;

@RestController

@AllArgsConstructor
public class ReservationRestController {
    IReservation iReservation;
    INotification iNotification ;


    @PostMapping("/AddRes")

    public Reservation addRes(@RequestBody Reservation A) throws MessagingException {
       // iReservation.sendHtmlEmail(A);
        return iReservation.addRes(A);
    }

    @PutMapping("/UpdateRes")
    public Reservation updateRes(@RequestBody Reservation A) {
        return iReservation.updateRes(A);
    }

    @GetMapping("/GetResByID/{id}")
    public Reservation retrieveNotById(@PathVariable("id") long id) {
        return iReservation.retrieveResById(id);
    }

    @GetMapping("/GetAllRes")

    public List<Reservation> retrieveAllRes()  { return iReservation.retrieveAllRes(); }

    @DeleteMapping("/DeleteRes/{id}")
    public void deleteRes(@PathVariable("id") long id) {
        iReservation.deleteRes(id);
    }

    @GetMapping("/Getremainder")
    @Scheduled(fixedRate = 30000L)
    public List<Reservation> findReservationsBefore24Hours() {
        List<Reservation> reservations = iReservation.findReservationsBefore24Hours();
        for (Reservation reservation : reservations) {
            Notification notification = new Notification();
            iNotification.addNot(notification);
            System.out.println("addnot");
        }
        return reservations;
    }





}

