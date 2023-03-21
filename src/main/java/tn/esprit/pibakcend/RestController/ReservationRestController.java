package tn.esprit.pibakcend.RestController;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.IReservation;
import tn.esprit.pibakcend.entities.Notification;
import tn.esprit.pibakcend.entities.Reservation;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReservationRestController {
    IReservation iReservation;

    @PostMapping("/AddRes")
    public Reservation addRes(@RequestBody Reservation A) {
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
    public List<Reservation> retrieveAllRes() {
        return iReservation.retrieveAllRes();
    }

    @DeleteMapping("/DeleteRes/{id}")
    public void deleteRes(@PathVariable("id") long id) {
        iReservation.deleteRes(id);
    }
}

