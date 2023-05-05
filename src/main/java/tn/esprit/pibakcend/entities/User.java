package tn.esprit.pibakcend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
//// Constructor par defaults
@NoArgsConstructor
@ToString

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String password;
    private String address;
    private String telNumber;
    @Temporal (TemporalType.DATE)
    private Date birthDate ;
    @ManyToMany
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Publication> publications ;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires;

    @ManyToMany(mappedBy = "favoriteUsers")
    @JsonIgnore

    private Set<Publication> favoritePublications = new HashSet<>();

}
