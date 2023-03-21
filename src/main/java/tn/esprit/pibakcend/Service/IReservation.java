package tn.esprit.pibakcend.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.Repository.ReservationRepository;
import tn.esprit.pibakcend.entities.Reservation;

import java.util.List;

public interface IReservation  {
    public Reservation addRes(Reservation A);
    public Reservation updateRes(Reservation A);
    public Reservation retrieveResById(long id);

    public List<Reservation> retrieveAllRes();

    public void deleteRes(long id) ;
}
