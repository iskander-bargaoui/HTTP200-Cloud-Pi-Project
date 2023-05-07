package tn.esprit.pibakcend.RestController;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.security.services.INotification;
import tn.esprit.pibakcend.entities.Notification;

import java.util.List;

@RestController
@CrossOrigin(origins = "//localhost:4200")
@RequestMapping("/api")
@AllArgsConstructor

public class NotificationRestController {
    INotification iNotification;

    @PostMapping("/AddNot")
    public Notification addNot(@RequestBody Notification A) {
        return iNotification.addNot(A);
    }

    @PutMapping("/UpdateNot")
    public Notification updateNot(@RequestBody Notification A) {
        return iNotification.updateNot(A);
    }

    @GetMapping("/GetNotByID/{id}")
    public Notification retrieveNotById(@PathVariable("id") long id) {
        return iNotification.retrieveNotById(id);
    }

    @GetMapping("/GetAllNot")
    public List<Notification> retrieveAllNot() {
        return iNotification.retrieveAllNot();
    }

    @DeleteMapping("/DeleteNot/{id}")
    public void deleteNot(@PathVariable("id") long id) {
        iNotification.deleteNot(id);
    }
}
 ////hhhhhhhhhhhhhhhhhhhh