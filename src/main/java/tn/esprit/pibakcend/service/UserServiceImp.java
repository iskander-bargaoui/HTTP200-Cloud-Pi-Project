package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import javax.transaction.Transactional;
import javax.xml.ws.soap.Addressing;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImp implements IUser{
    UserRepository userRepository;
    PublicationRepository publicationRepository;
    @Override
    public User toggleFavoritePublication(Long idUser, Integer idPub) {
        User user = userRepository.findById(idUser).orElse(null);
        Publication publication =  publicationRepository.findById(idPub).orElse(null);
        Set<Publication> favoritePublications = user.getFavoritePublications();
        if (favoritePublications.contains(publication)) {
            favoritePublications.remove(publication);
            publication.setFavorite(false);
            publication.setFavoriteDate(null);
        } else {
            publication.setFavorite(true);
            publication.setFavoriteDate(LocalDateTime.now());
            favoritePublications.add(publication);
        }
        user.setFavoritePublications(favoritePublications);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<Publication> getFavoritePublicationsByUserId(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
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

