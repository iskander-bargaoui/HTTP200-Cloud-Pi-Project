package tn.esprit.pibakcend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
//// Constructor par defaults
@NoArgsConstructor
@ToString

public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long idReservation ;
    public String nameR ;
    @Temporal(TemporalType.DATE)
    public Date dateReservetionR;
    @ManyToOne
    public Notification notification;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userrr;


}
