package tn.esprit.pibakcend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;


import javax.persistence.Entity;

@Entity
@Getter
@Setter
//// Constructor par defaults
@NoArgsConstructor
@ToString
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int value;

    @ManyToOne
    private Evenement event;

    @ManyToOne
    private Formation formationn;
}
