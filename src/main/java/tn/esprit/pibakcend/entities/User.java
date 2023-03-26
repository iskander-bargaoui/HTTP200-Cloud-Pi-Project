package tn.esprit.pibakcend.entities;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Publication> publications ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

}
