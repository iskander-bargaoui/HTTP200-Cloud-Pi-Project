package tn.esprit.pibakcend.security.services;

import tn.esprit.pibakcend.entities.Reservation;

import javax.mail.MessagingException;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface IReservation {
    public Reservation addRes(Reservation A);
    public Reservation updateRes(Reservation A);
    public Reservation retrieveResById(long id) throws AccessDeniedException;
    public void sendHtmlEmail(Reservation A)  throws MessagingException ;
    public List<Reservation> retrieveAllRes();

    public void deleteRes(long id) throws AccessDeniedException;

    public List<Reservation> findReservationsBefore24Hours () ;
}
