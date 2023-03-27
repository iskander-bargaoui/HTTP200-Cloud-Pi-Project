package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationServiceImp implements IPublication{
    PublicationRepository publicationRepository;
    UserRepository userRepository;
    @Override
    public Publication addPub(Publication pub) {
        return publicationRepository.save(pub);
    }

    @Override
    public Publication updatePub(Publication pub) {
        return publicationRepository.save(pub);
    }

    @Override
    public Publication retrievePublicationById(Integer id) {
        return publicationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Publication> retrieveAllPublication() {
        return publicationRepository.findAll();
    }

    @Override
    public void deletePublication(Integer id) {
    publicationRepository.deleteById(id);
    }
    @Override
    public List<Publication> retrievePublicationUserById(Long id) {
        return publicationRepository.findAll().stream().filter(x -> x.getUser().getId() == id).collect(Collectors.toList());
    }
    @Override
    public Publication assignPublicationToUser(Integer idPub, Long idUser) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);

        user.getPublications().add(publication);
        return publicationRepository.save(publication);

    }
}
