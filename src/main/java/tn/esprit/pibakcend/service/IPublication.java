package tn.esprit.pibakcend.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.util.List;


public interface IPublication {

    Publication addPub(Publication pub , Long idUser);
    Publication updatePub(Publication pub, Integer idPub);
    Publication retrievePublicationById(Integer idPub);
    List<Publication> retrieveAllPublication();
    void deletePublication(Integer idPub);
    List<Publication> retrievePublicationUserById (Long idUser);
    //Publication assignPublicationToUser (Integer idPub, Long idUser);

    String saveImage(MultipartFile file) throws Exception;

    public Publication toggleFavoritePublication(Long idUser, Integer idPub);

    public List<Publication> getFavoritePublicationsByUserId(Long idUser);

    public void deleteExpiredFavoritePublications();







}
