package project.management.usersmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data // @Getter + Setter + ToString + Equals and HashCode + RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    private String nom;

    @Size(max = 20)
    private String prenom;

    @NotBlank
    @Size(max = 20)
    @NotNull
    String username;
    //private boolean stateUser;

    @Email
    @Size(max=50)
    @NotNull
    private String email;

    @NotNull
    @Size(min = 4 , max = 20 )
    private String password;

    @Size (max = 50)
    private String address;

    @NotBlank
    // Twilio Validation API
    private String phoneNumber;

    // Control de saisie par date
    @Temporal(TemporalType.DATE)
    private Date birthDate ;

    private boolean connected;

    // Relation Many to Many with

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Constructor for the signup request

    public User(@Size(max = 80) String username, @Size(max = 50) @Email String email, @Size(max = 120) String password,
                String address, @Size(max = 50) String tel, @Size(max = 50) String nom, @Size(max = 50) String prenom, Date birthDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.birthDate=birthDate;

    }
}
