package tn.esprit.pibakcend.security.services;

import tn.esprit.pibakcend.entities.Notification;

import java.util.List;

public interface INotification {
    public Notification addNot(Notification A);
    public Notification updateNot(Notification A);
    public Notification retrieveNotById(long id);

    public List<Notification> retrieveAllNot();

    public void deleteNot(long id) ;
}
