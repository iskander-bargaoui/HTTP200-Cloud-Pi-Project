package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
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
        } else {
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
}
