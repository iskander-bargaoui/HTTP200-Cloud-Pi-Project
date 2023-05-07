package tn.esprit.pibakcend.security.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.nio.file.AccessDeniedException;
import java.util.List;


public interface IPublication {

    Publication addPub(Publication pub );
    Publication updatePub(Publication pub, Integer idPub);
    Publication retrievePublicationById(Integer idPub);
    List<Publication> retrieveAllPublication();
    void deletePublication(Integer idPub) throws AccessDeniedException;
    List<Publication> retrievePublicationUserById ();
    //Publication assignPublicationToUser (Integer idPub, Long idUser);

    String saveImage(MultipartFile file) throws Exception;

    public Publication toggleFavoritePublication( Integer idPub);

    public List<Publication> getFavoritePublicationsByUserId();

    public void deleteExpiredFavoritePublications();







}
