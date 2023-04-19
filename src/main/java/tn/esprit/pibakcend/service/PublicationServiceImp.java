package tn.esprit.pibakcend.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.*;
import tn.esprit.pibakcend.Repository.PublicationRepository;
import tn.esprit.pibakcend.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationServiceImp implements IPublication{
    PublicationRepository publicationRepository;
    UserRepository userRepository;


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

