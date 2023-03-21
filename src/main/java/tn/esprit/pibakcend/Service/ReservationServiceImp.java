package tn.esprit.pibakcend.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.ReservationRepository;
import tn.esprit.pibakcend.entities.Reservation;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImp implements IReservation{
ReservationRepository reservationRepository ;


    @Override
    public Reservation addRes(ReservationRepository A) {
        return null;
    }

    @Override
    public Reservation updateRes(ReservationRepository A) {
        return null;
    }

    @Override
    public Reservation retrieveResById(long id) {
        return null;
    }

    @Override
    public List<Reservation> retrieveAllRes() {
        return null;
    }

    @Override
    public void deleteRes(long id) {

    }
}
