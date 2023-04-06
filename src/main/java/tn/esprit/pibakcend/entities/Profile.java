package tn.esprit.pibakcend.entities;

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
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String sexe;
    @Enumerated(EnumType.STRING)
    private Categorie categorie;
    private boolean isVerified;
    private String photoprofile;



    @OneToMany
    @JsonIgnore
    private List<Feedback> feedbackList = new ArrayList<>();

}
