package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer>{
    List<Feedback> findByProfile(Profile profile);
    List<Feedback> findByRating(int rating);

    List<Feedback> findTopByRating(Integer limit);


}
