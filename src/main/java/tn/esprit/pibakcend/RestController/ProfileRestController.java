package tn.esprit.pibakcend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.IFeedback;
import tn.esprit.pibakcend.Service.IProfile;
import tn.esprit.pibakcend.entities.Categorie;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.Profile;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProfileRestController {
    IProfile iProfile;

    @PostMapping("/addProfile")
    public Profile addProfile(@RequestBody Profile profile) {
        return iProfile.addProfile(profile);
    }

    @GetMapping("/Profiles")
    public List<Profile> retrieveAllProfile() {
        return iProfile.retrieveAllProfile();

    }

    @GetMapping("/ProfileBYID/{id}")
    public Profile retrieveProfileById(@PathVariable("id") Integer id) {
        return iProfile.retrieveProfileById(id);

    }

    @PutMapping("/updateProfile")
    public Profile updateProfile(@RequestBody Profile profile) {
        return iProfile.updateProfile(profile);
    }

    @DeleteMapping("/deleteProfile/{id}")
    public void deleteProfile(@PathVariable("id") Integer id) {
        iProfile.deleteProfile(id);

    }

    @GetMapping("/getProfileByUsername/{username}")
    public Profile getProfileByUsername(@PathVariable("username") String username) {
        Profile profile = iProfile.findByUsername(username);
        return profile;
    }

    @GetMapping("/getProfilesByCategorie/{categorie}")
    public List<Profile> getProfilesByCategorie(@PathVariable("categorie") Categorie categorie) {
        List<Profile> profiles = iProfile.findByCategorie(categorie);
        return profiles;
    }

    @GetMapping("/getProfilesByIsVerified/{isVerified}")
    public List<Profile> getProfilesByIsVerified(@PathVariable("isVerified")  boolean isVerified) {
        List<Profile> profiles = iProfile.findByIsVerified(isVerified);
        return profiles;
    }

    @GetMapping("/Profiles/best")
    public Profile getBestProfile() {
      return iProfile.getBestProfile();
    }


}
