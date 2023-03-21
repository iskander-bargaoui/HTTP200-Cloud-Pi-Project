package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.repository.PublicationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PublicationServiceImp implements IPublication{
    PublicationRepository publicationRepository;
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
}
