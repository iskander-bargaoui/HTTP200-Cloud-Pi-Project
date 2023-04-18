package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.Reservation;

import javax.mail.MessagingException;
import java.util.List;

public interface IReservation  {
    public Reservation addRes(Reservation A);
    public Reservation updateRes(Reservation A);
    public Reservation retrieveResById(long id);
    public void sendHtmlEmail(Reservation A)  throws MessagingException ;
    public List<Reservation> retrieveAllRes();

    public void deleteRes(long id) ;

    public List<Reservation> findReservationsBefore24Hours () ;
}
