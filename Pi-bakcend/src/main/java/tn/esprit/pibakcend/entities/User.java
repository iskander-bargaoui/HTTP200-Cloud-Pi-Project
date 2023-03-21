package tn.esprit.pibakcend.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    String username;
    private String email;
    private String password;
    private String address;
    private String telNumber;
    @Temporal (TemporalType.DATE)
    private Date birthDate ;
    @ManyToMany
    private Set<Role> roles;

}
