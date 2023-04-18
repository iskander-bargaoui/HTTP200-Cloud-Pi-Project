package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.*;

import java.util.List;

public interface IActivity {
    public void logActivity(User user, ActivityType activityType, Publication publication, Commentaire commentaire);
}
