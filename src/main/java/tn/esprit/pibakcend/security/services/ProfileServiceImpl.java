package tn.esprit.pibakcend.security.services;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.Repository.FeedbackRepository;
import tn.esprit.pibakcend.Repository.ProfileRepository;
import tn.esprit.pibakcend.Repository.UserRepository;
import tn.esprit.pibakcend.entities.*;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements IProfile {

    ProfileRepository profileRepository;
    FeedbackRepository feedbackRepository;
    UserRepository userRepository;


    @Override
    public Profile addProfile(Profile profile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    @Override
    public Profile retrieveProfileById(Integer id) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Profile> profileOptional = profileRepository.findById(id);
        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            if (profile.getUser().equals(user)) {
                return profile;
            } else {
                throw new AccessDeniedException("You do not have permission to access this profile");
            }
        } else {
            throw new EntityNotFoundException("Profile with id " + id + " not found");
        }
    }

    @Override
    public List<Profile> retrieveAllProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return profileRepository.findAllByUser(user);
    }

    @Override
    public void deleteProfile(Integer id) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Profile> profileOptional = profileRepository.findById(id);
        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            if (profile.getUser().equals(user)) {
                profileRepository.deleteById(id);
            } else {
                throw new AccessDeniedException("You do not have permission to delete this event");
            }
        } else {
            throw new EntityNotFoundException("profile with id " + id + " not found");
        }
    }

    @Override
    public Profile findByUsername(String username) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Optional<User> currentUserOptional = userRepository.findByUsername(currentUsername);
        User currentUser = currentUserOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println(currentUser);

        Profile profile = profileRepository.findByUsername(username);
        if (profile == null) {
            throw new EntityNotFoundException("Profile for user " + username + " not found");
        } else if (profile.getUser().equals(currentUser)) {
            return profile;
        } else {
            throw new AccessDeniedException("You do not have permission to access this profile");
        }
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

    public String saveImage(MultipartFile file) throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<User> userOptional = userRepository.findByUsername(username);
            User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            String uploadDir = "C:/Users/HP/Downloads/HTTP200-PI-Front-Ghazi/src/assets/images";

            // Create a subdirectory with the user's ID or username
            uploadDir += "/" + user.getId(); // or user.getUsername()

            FileUploadUtil.saveFile(uploadDir, fileName, file);

            String imagePath = uploadDir + "/" + fileName;

            return imagePath;
        } catch (IOException ex) {
            throw new Exception("Could not save image: " + ex.getMessage());
        }
    }
}
