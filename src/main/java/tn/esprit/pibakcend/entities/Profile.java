package tn.esprit.pibakcend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String sexe;
    @Enumerated(EnumType.STRING)
    private Categorie categorie;
    private boolean isVerified=false;
    private String photoprofile;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Feedback> feedbackList;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;
    }
