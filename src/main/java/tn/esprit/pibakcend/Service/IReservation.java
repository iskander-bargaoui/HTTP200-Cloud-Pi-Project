package tn.esprit.pibakcend.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.Repository.ReservationRepository;
import tn.esprit.pibakcend.entities.Reservation;

import java.util.List;

public interface IReservation  {
    Reservation addRes(ReservationRepository A);
    Reservation updateRes(ReservationRepository A);
    Reservation retrieveResById(long id);
    List<Reservation> retrieveAllRes();

    void deleteRes(long id);
}
