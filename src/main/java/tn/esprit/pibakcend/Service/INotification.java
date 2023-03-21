package tn.esprit.pibakcend.Service;

import org.aspectj.weaver.ast.Not;
import tn.esprit.pibakcend.entities.Notification;
import tn.esprit.pibakcend.entities.Reservation;

import java.util.List;

public interface INotification {
    public Notification addNot(Notification A);
    public Notification updateNot(Notification A);
    public Notification retrieveNotById(long id);

    public List<Notification> retrieveAllNot();

    public void deleteNot(long id) ;
}
