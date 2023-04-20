package tn.esprit.pibakcend.RestController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.Service.FeedbackServiceImpl;
import tn.esprit.pibakcend.Service.IFeedback;
import tn.esprit.pibakcend.Service.IProfile;
import tn.esprit.pibakcend.Service.ProfileServiceImpl;
import tn.esprit.pibakcend.entities.Categorie;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.FileUploadUtil;
import tn.esprit.pibakcend.entities.Profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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
    public List<Profile> getProfilesByIsVerified(@PathVariable("isVerified") boolean isVerified) {
        List<Profile> profiles = iProfile.findByIsVerified(isVerified);
        return profiles;
    }

    @GetMapping("/Profiles/best")
    public Profile getBestProfile() {
        return iProfile.getBestProfile();
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam("id") Integer id) {
        try {
            String imagePath = iProfile.saveImage(file, id);
            return ResponseEntity.ok().body("Image uploaded successfully. Image Path : " + imagePath);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}





