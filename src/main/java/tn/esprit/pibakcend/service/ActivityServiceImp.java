package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.repository.ActivityRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActivityServiceImp implements IActivity{

    UserRepository userRepository;

    ActivityRepository activityRepository;

    @Override
    public void logActivity(User user, ActivityType activityType, Publication publication, Commentaire commentaire) {
        Activity activity = new Activity();
        activity.setUser(user);
        activity.setActivityType(activityType);
        activity.setActivityTime(LocalDateTime.now());
        activity.setPublication(publication);
        activity.setCommentaire(commentaire);
        activityRepository.save(activity);
    }


    // Other methods
}
