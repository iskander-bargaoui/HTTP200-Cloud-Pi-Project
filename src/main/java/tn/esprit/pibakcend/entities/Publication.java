package tn.esprit.pibakcend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "publication")
public class Publication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPub;
    @Column(nullable = false)
    private String contenuPub;
    @Column(nullable = false)
    private String titrePub;
    @Enumerated(EnumType.STRING)
    private Visibilite vis;
    //@Temporal(TemporalType.DATE)
    @Column(name = "date_posted")
    private LocalDate dateCreationPub;

    private String image;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

}
