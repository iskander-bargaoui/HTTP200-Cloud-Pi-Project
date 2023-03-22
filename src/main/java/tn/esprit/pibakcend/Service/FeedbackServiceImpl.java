package tn.esprit.pibakcend.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.FeedbackRepository;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements IFeedback{
    
    FeedbackRepository feedbackRepository;
    @Override
    public Feedback addFeedback(Feedback Feedback) {
        return feedbackRepository.save(Feedback);
    }

    @Override
    public Feedback updateFeedback(Feedback Feedback) {
        Feedback.setUpdatedDate(new Date());
        return feedbackRepository.save(Feedback);
    }

    @Override
    public Feedback retrieveFeedbackById(Integer id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public List<Feedback> retrieveAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public void deleteFeedback(Integer id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<Feedback> findByProfile(Profile profile) {
        return feedbackRepository.findByProfile(profile);
    }

    @Override
    public List<Feedback> findByRating(int rating) {
        return feedbackRepository.findByRating(rating);
    }

    @Override
    public List<Feedback> findTopByRating (Integer limit) {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        Collections.sort(feedbackList, (f1, f2) -> Double.compare(f2.getRating(), f1.getRating()));
        limit=3;
        List<Feedback> topRatedFeedback = feedbackList.stream().limit(limit).collect(Collectors.toList());
        return topRatedFeedback;
    }

    @Override
    public Double getAverageRatingByProfile(Profile profile) {
        List<Feedback> feedbackList = feedbackRepository.findByProfile(profile);
        Double averageRating = feedbackList.stream()
                .mapToDouble(Feedback::getRating)
                .average()
                .orElse(Double.NaN);
        return averageRating;
    }

}
