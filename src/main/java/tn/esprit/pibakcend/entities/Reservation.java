package tn.esprit.pibakcend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable {
    @Id
    public int idReservation ;
    @Temporal(TemporalType.DATE)
    private Date dateReservetion;
    public int idUser ;
}
