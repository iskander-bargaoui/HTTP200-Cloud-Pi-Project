package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer>{
    List<Feedback> findByProfile(Profile profile);
    List<Feedback> findByRating(int rating);

    List<Feedback> findTopByRating(Integer limit);

}
