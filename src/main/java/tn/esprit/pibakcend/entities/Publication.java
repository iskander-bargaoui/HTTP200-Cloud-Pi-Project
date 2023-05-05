package tn.esprit.pibakcend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    private String contenuPub;

    private String titrePub;
    @Enumerated(EnumType.STRING)
    private Visibilite vis;
    //@Temporal(TemporalType.DATE)
    @Column(name = "date_posted")
    private LocalDate dateCreationPub;
    @Nullable
    private String image;
    private Integer likeCount;
    private Integer dislikeCount;

    private boolean isFavorite;

    @Column(name = "favorite_date")
    private LocalDateTime favoriteDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @JsonIgnore

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires;

    @JsonIgnore
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private Set<Like> likes;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "favorite_publications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "id_Pub"))
    private Set<User> favoriteUsers = new HashSet<>();

}
