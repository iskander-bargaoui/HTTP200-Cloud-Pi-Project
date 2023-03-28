package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {


}
