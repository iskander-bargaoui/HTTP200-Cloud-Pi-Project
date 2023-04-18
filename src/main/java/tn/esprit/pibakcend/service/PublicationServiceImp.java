package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.repository.ActivityRepository;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationServiceImp implements IPublication{
    PublicationRepository publicationRepository;
    UserRepository userRepository;

    IActivity iActivity;
    ActivityRepository activityRepository;

    @Override
    public Publication addPub(Publication pub, Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        pub.setUser(user);
        Publication createdPub= publicationRepository.save(pub);
       // iActivity.logActivity(user, ActivityType.CREATE_PUBLICATION, createdPub, null);
        return  createdPub;
    }

    @Override
    public Publication updatePub(Publication pub,Integer idPub) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);
        pub.setIdPub(idPub);
        pub.setUser(publication.getUser());
        return publicationRepository.save(pub);
    }

    @Override
    public Publication retrievePublicationById(Integer idPub) {
        return publicationRepository.findById(idPub).orElse(null);
    }

    @Override
    public List<Publication> retrieveAllPublication() {
        return publicationRepository.findAll();
    }

    @Override
    public void deletePublication(Integer idPub) {
    publicationRepository.deleteById(idPub);
    }
    @Override
    public List<Publication> retrievePublicationUserById(Long idUser) {
        return publicationRepository.findAll().stream().filter(x -> x.getUser().getId() == idUser).collect(Collectors.toList());
    }


}

