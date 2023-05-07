package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import java.io.IOException;
import java.util.List;

public interface IFeedback {
    Feedback addFeedback(Feedback feedback) throws IOException;
    Feedback updateFeedback(Feedback feedback) throws IOException;
    Feedback retrieveFeedbackById(Integer id);
    List<Feedback> retrieveAllFeedback();
    void deleteFeedback(Integer id);
    List<Feedback> findByProfile(Profile profile);
    List<Feedback> findByRating(int rating);
    List<Feedback>findTopByRating(Profile profile);
    public Double getAverageRatingByProfile(Profile profile);
    public String Badwords(String text) throws IOException;
}
