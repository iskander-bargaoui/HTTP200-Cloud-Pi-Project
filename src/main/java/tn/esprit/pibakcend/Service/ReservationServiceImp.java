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
    public Reservation addRes(Reservation A) {
         return reservationRepository.save(A);
    }

    @Override
    public Reservation updateRes(Reservation A) {
        return reservationRepository.save(A);
    }

    @Override
    public Reservation retrieveResById(long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> retrieveAllRes() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteRes(long id) {
        reservationRepository.deleteById(id);
    }
}
