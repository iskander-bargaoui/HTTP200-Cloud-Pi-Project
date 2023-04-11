package com.example.pi.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
@Entity
public class Notification implements Serializable {
    @Id
    public String message ;
    @Temporal(TemporalType.DATE)
    public  Date dateNotification ;
    public int idSource ;
}
