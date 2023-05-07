package tn.esprit.pibakcend.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
//// Constructor par defaults
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long idNotification ;
    public String message ;
    public String namen;
    public long idsender ;
    public long idreciver ;
    @Temporal(TemporalType.DATE)
    public  Date dateNotification ;

}
