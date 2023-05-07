package tn.esprit.pibakcend.security.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.NotificationRepository;
import tn.esprit.pibakcend.entities.Notification;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImp implements INotification {
    NotificationRepository notificationRepository;

    @Override

    public Notification addNot(Notification N) {

        return notificationRepository.save(N);
    }

    @Override
    public Notification updateNot(Notification N) {
        return notificationRepository.save(N);
    }

    @Override
    public Notification retrieveNotById(long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Notification> retrieveAllNot() {
        return notificationRepository.findAll();
    }

    @Override
    public void deleteNot(long id) {
        notificationRepository.deleteById(id);
    }
}
