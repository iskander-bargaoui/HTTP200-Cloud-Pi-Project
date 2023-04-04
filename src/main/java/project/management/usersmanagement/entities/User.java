package project.management.usersmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.management.usersmanagement.token.Token;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data // @Getter + Setter + ToString + Equals and HashCode + RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })

public class User implements UserDetails {

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

    @Email
    private String email;

    @NotNull
    @Size(min = 4 , max = 20 )
    private String password;

    @Size (max = 50)
    private String address;

    @NotBlank
    // Twilio Validation API
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    private Date birthDate ;

    // Relation Many to Many with

    @OneToMany(mappedBy = "UserAuth" ,fetch = FetchType.EAGER)
    private Set<Role> roles;



    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (Role role : roles) {
            if (role !=null)
                authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
            else
                System.out.println("----- Access Denied! Non authorized user! ----");
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public Set<Role> getAuthFromBase(){
        return this.roles;
    }
}
