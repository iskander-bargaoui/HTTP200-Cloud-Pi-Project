package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.pibakcend.entities.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT res FROM Reservation res WHERE res.dateReservetion >= CURRENT_DATE AND res.dateReservetion < CURRENT_DATE + 1")
   public List<Reservation> findReservationsBefore24Hours();
}
