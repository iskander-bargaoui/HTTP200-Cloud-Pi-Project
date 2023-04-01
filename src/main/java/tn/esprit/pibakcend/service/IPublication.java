package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import java.util.List;


public interface IPublication {

    Publication addPub(Publication pub);
    Publication updatePub(Publication pub);
    Publication retrievePublicationById(Integer idPub);
    List<Publication> retrieveAllPublication();
    void deletePublication(Integer idPub);
    List<Publication> retrievePublicationUserById (Long idUser);

    Publication assignPublicationToUser (Integer idPub, Long idUser);







}
