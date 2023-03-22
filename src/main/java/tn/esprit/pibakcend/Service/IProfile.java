package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.Categorie;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import java.util.List;

public interface IProfile {
    Profile addProfile(Profile profile);
    Profile updateProfile(Profile profile);
    Profile retrieveProfileById(Integer id);
    List<Profile> retrieveAllProfile();
    void deleteProfile(Integer id);
    public Profile findByUsername(String username);

    List<Profile> findByCategorie(Categorie categorie);

    List<Profile> findByIsVerified(boolean isVerified);
    public Profile getBestProfile();
}
