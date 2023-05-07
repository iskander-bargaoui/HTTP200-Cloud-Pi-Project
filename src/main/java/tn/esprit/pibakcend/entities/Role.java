package tn.esprit.pibakcend.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data // @Getter + Setter + ToString + Equals and HashCode + RequiredArgsConstructor
@Table(name = "roles")
@AllArgsConstructor
@Entity

public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role() {
    }
    public Role(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

}
