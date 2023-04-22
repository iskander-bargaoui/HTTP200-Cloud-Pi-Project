package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
