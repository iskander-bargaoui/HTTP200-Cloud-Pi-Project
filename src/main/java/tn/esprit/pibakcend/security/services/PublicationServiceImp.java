package tn.esprit.pibakcend.security.services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.Repository.PublicationRepository;
import tn.esprit.pibakcend.Repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationServiceImp implements IPublication{
    PublicationRepository publicationRepository;
    UserRepository userRepository;


    @Override
    public Publication addPub(Publication pub) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        pub.setUser(user);
        Publication createdPub= publicationRepository.save(pub);
       // iActivity.logActivity(user, ActivityType.CREATE_PUBLICATION, createdPub, null);
        return  createdPub;
    }

    @Override
    public Publication updatePub(Publication pub,Integer idPub) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);
        pub.setIdPub(idPub);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        pub.setUser(user);
        return publicationRepository.save(pub);
    }

    @Override
    public Publication retrievePublicationById(Integer idPub) {
        return publicationRepository.findById(idPub).orElse(null);
    }

    @Override
    public List<Publication> retrieveAllPublication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return publicationRepository.findAll();
    }

    @Override
    public void deletePublication(Integer idPub) throws AccessDeniedException {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Publication> optionalPublication = publicationRepository.findById(idPub);
        if (optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();
            if (publication.getUser().equals(user)) {
                publicationRepository.deleteById(idPub);
            } else {
                throw new AccessDeniedException("You do not have permission to delete this event");
            }
        } else {
            throw new EntityNotFoundException("Event with id " + idPub + " not found");
        }    }
    @Override
    public List<Publication> retrievePublicationUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return publicationRepository.findAll().stream().filter(x -> x.getUser().getId() == user.getId()).collect(Collectors.toList());
    }

    public String saveImage(MultipartFile file) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        try {

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            String uploadDir = "E:/Desktop/HTTP200-PI-Front/src/assets/images" ;

            FileUpload.saveFile(uploadDir, fileName, file);

            String imagePath = uploadDir + "/" + fileName;

            return imagePath;
        } catch (IOException ex) {
            throw new Exception("Could not save image: " + ex.getMessage());
        }
    }

    @Override
    public Publication toggleFavoritePublication( Integer idPub) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user == null) {
            throw new IllegalArgumentException("User not found with id " + user.getId());
        }
        Publication publication = publicationRepository.findById(idPub).orElse(null);
        if (publication == null) {
            throw new IllegalArgumentException("Publication not found with id " + idPub);
        }
        Set<User> favoriteUsers = publication.getFavoriteUsers();
        if (favoriteUsers == null) {
            favoriteUsers = new HashSet<>();
            publication.setFavoriteUsers(favoriteUsers);
        }
        if (favoriteUsers.contains(user)) {
            favoriteUsers.remove(user);
            publication.setFavorite(false);
            publication.setFavoriteDate(null);
        } else {
            publication.setFavorite(true);
            publication.setFavoriteDate(LocalDateTime.now());
            favoriteUsers.add(user);
        }
        publicationRepository.save(publication);
        return publication;
    }



    @Override
    public List<Publication> getFavoritePublicationsByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new ArrayList<>(user.getFavoritePublications());

    }
    @Transactional
    @Scheduled(fixedRate = 60000)
    //@Scheduled(cron = "0 0 0 1 */3 *")
    @Override
    public void deleteExpiredFavoritePublications() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(1);
        List<Publication> expiredPublications = publicationRepository.findByIsFavoriteTrueAndFavoriteDateBefore(expirationTime);

        for (Publication publication : expiredPublications) {
            Set<User> favoriteUsers = publication.getFavoriteUsers();
            for (User user : favoriteUsers) {
                Set<Publication> favoritePublications = user.getFavoritePublications();
                favoritePublications.remove(publication);
                user.setFavoritePublications(favoritePublications);
                userRepository.save(user);
            }
            publication.setFavoriteUsers(new HashSet<>());
            publication.setFavorite(false);
            publication.setFavoriteDate(null);
            publicationRepository.save(publication);
        }
    }
}

