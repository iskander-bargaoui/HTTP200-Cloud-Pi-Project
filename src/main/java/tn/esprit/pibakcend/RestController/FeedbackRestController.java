package tn.esprit.pibakcend.RestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.IFeedback;
import tn.esprit.pibakcend.Service.IProfile;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import java.util.List;

@RestController
@AllArgsConstructor
public class FeedbackRestController {
    IFeedback iFeedback;
    IProfile iProfile;
    @PostMapping("/addFeedback")
    public Feedback addFeedback(@RequestBody Feedback Feedback){
        return iFeedback.addFeedback(Feedback);
    }
    @GetMapping("/Feedbacks")
    public List<Feedback> retrieveAllFeedback(){
        return iFeedback.retrieveAllFeedback();

    }

    @GetMapping("/FeedbackBYID/{id}")
    public Feedback retrieveFeedbackById(@PathVariable("id") Integer id){
        return iFeedback.retrieveFeedbackById(id);

    }

    @PutMapping("/updateFeedback")
    public Feedback updateFeedback(@RequestBody Feedback Feedback){
        return iFeedback.updateFeedback(Feedback);
    }

    @DeleteMapping("/deleteFeedback/{id}")
    public void deleteFeedback(@PathVariable("id") Integer id){
        iFeedback.deleteFeedback(id);

    }

    @GetMapping("/feedbacks/byProfile/{username}")
    public List<Feedback> findByProfile(@PathVariable("username") String username) {
        Profile profile = iProfile.findByUsername(username);
        List<Feedback> feedbackList = iFeedback.findByProfile(profile);
        return feedbackList;
    }
    @GetMapping("/feedbacks/byRating/{rating}")
    public List<Feedback> findByRating(@PathVariable("rating") int rating) {
        List<Feedback> feedbackList = iFeedback.findByRating(rating);
        return feedbackList;
    }

    @GetMapping("/feedbacks/topRated")
    public List<Feedback> findTopByRating(Integer limit) {
        List<Feedback> feedbackList = iFeedback.findTopByRating(limit);
        return feedbackList;
    }

    @GetMapping("/feedbacks/averageRating/{username}")
    public Double AverageRatingperFeedback(@PathVariable("username") String username) {
        Profile profile = iProfile.findByUsername(username);
        return iFeedback.getAverageRatingByProfile(profile);
    }



}
