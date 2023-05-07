package tn.esprit.pibakcend.security.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.entities.Categorie;
import tn.esprit.pibakcend.entities.Profile;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface IProfile {
    Profile addProfile(Profile profile);
    Profile updateProfile(Profile profile);
    Profile retrieveProfileById(Integer id) throws AccessDeniedException;
    List<Profile> retrieveAllProfile();
    void deleteProfile(Integer id) throws AccessDeniedException;
    public Profile findByUsername(String username) throws AccessDeniedException;

    List<Profile> findByCategorie(Categorie categorie);

    List<Profile> findByIsVerified(boolean isVerified);
    public Profile getBestProfile();

    String saveImage(MultipartFile file) throws Exception;

}
