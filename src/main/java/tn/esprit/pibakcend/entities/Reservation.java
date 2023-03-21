package tn.esprit.pibakcend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
//// Constructor par defaults
@NoArgsConstructor
@ToString
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idReservation ;
    @Temporal(TemporalType.DATE)
    public Date dateReservetion;
    public int idUser ;

    @ManyToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    public List<Profile> profileList = new ArrayList<>();

    @ManyToOne
    public Notification notification;
}
