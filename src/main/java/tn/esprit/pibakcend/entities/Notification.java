package tn.esprit.pibakcend.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;
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
    public long idnotification ;
    public String message ;
    public long idsender ;
    public long idreciver ;
    @Temporal(TemporalType.DATE)
    public  Date dateNotification ;

}