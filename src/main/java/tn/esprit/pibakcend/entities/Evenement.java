package tn.esprit.pibakcend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvenement;
    private String titreEvennement ;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String Lieu;

    @ManyToMany (mappedBy = "evenements")
    @JsonIgnore
    private Set<User> utilisateurs;

    @ManyToMany(mappedBy = "evenements")
    @JsonIgnore
    private Set<Formation> formations;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @Column(nullable = false)
    private int rating = 0;

    public void rate(int value) {
        int numRatings = this.ratings.size();
        double avgRating = this.ratings.stream()
                .mapToInt(Rating::getValue)
                .average()
                .orElse(0);
        double expectedScore = 1.0 / (1 + Math.pow(10, (avgRating - value) / 400.0));
        double kFactor = 32 - Math.min(numRatings, 10) * 2.5;
        double newRating = this.rating + kFactor * (value - expectedScore);
        this.rating = (int) Math.round(newRating);
    }


}
