package project.management.usersmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data // @Getter + Setter + ToString + Equals and HashCode + RequiredArgsConstructor
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@Entity

public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    public Role() {
    }
     public Role(RoleEnum name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public RoleEnum getName() {
        return name;
    }
    public void setName(RoleEnum name) {
        this.name = name;
    }
    public Role orElseThrow(Object object) {
        // TODO Auto-generated method stub
        return null;
    }
    @ManyToOne
    @JsonIgnore
    private User UserAuth;

}
