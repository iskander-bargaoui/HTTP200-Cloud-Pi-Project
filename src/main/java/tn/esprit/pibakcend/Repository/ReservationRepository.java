package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.pibakcend.entities.Profile;
import tn.esprit.pibakcend.entities.Reservation;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT res FROM Reservation res WHERE res.dateReservetionR >= CURRENT_DATE AND res.dateReservetionR < CURRENT_DATE + 1")
   public List<Reservation> findReservationsBefore24Hours();
    List<Reservation> findAllByuserrr(User user);


}
