package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import java.util.List;

public interface IFeedback {
    Feedback addFeedback(Feedback feedback);
    Feedback updateFeedback(Feedback feedback);
    Feedback retrieveFeedbackById(Integer id);
    List<Feedback> retrieveAllFeedback();
    void deleteFeedback(Integer id);
    List<Feedback> findByProfile(Profile profile);
    List<Feedback> findByRating(int rating);
    List<Feedback>findTopByRating(Integer limit);

    public Double getAverageRatingByProfile(Profile profile);
}
