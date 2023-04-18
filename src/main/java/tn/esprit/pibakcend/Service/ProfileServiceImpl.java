package tn.esprit.pibakcend.Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.Repository.FeedbackRepository;
import tn.esprit.pibakcend.Repository.ProfileRepository;
import tn.esprit.pibakcend.entities.Categorie;
import tn.esprit.pibakcend.entities.Feedback;
import tn.esprit.pibakcend.entities.FileUploadUtil;
import tn.esprit.pibakcend.entities.Profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements IProfile {

    ProfileRepository profileRepository;
    FeedbackRepository feedbackRepository;

    @Override
    public Profile addProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile retrieveProfileById(Integer id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Override
    public List<Profile> retrieveAllProfile() {
        return profileRepository.findAll();
    }

    @Override
    public void deleteProfile(Integer id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile findByUsername(String username) {
       return profileRepository.findByUsername(username);
    }

    @Override
    public List<Profile> findByCategorie(Categorie categorie) {
        return profileRepository.findByCategorie(categorie);
    }

    @Override
    public List<Profile> findByIsVerified(boolean isVerified) {
        return profileRepository.findByIsVerified(isVerified);
    }

    @Override
    public Profile getBestProfile() {
         List<Profile> profiles =profileRepository.findAll();
            Profile bestProfile = null;
            double highestScore = 0.0;

            for (Profile profile : profiles) {
                double score = 0.0;
                double totalRating = 0.0;
                int feedbackCount = 0;

                for (Feedback feedback : profile.getFeedbackList()) {
                    totalRating += feedback.getRating();
                    feedbackCount++;
                }

                if (feedbackCount > 0) {
                    double averageRating = totalRating / feedbackCount;
                    score = averageRating * 0.6 + feedbackCount * 0.4;
                }

                if (score > highestScore) {
                    highestScore = score;
                    bestProfile = profile;
                }
            }

            return bestProfile;
        }

    public String saveImage(MultipartFile file, Integer id) throws Exception {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Profile profile = profileRepository.findById(id)
                    .orElseThrow(() -> new Exception("Profile not found with id " + id));

            String uploadDir = "profile-images/" + profile.getUsername();

            FileUploadUtil.saveFile(uploadDir, fileName, file);

            String imagePath = uploadDir + "/" + fileName;
            profile.setPhotoprofile(imagePath);
            profileRepository.save(profile);

            return imagePath;
        } catch (IOException ex) {
            throw new Exception("Could not save image: " + ex.getMessage());
        }
    }
}
