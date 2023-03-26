package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.Like;
import tn.esprit.pibakcend.entities.Publication;
import java.util.List;


public interface IPublication {

    Publication addPub(Publication pub);
    Publication updatePub(Publication pub);
    Publication retrievePublicationById(Integer id);
    List<Publication> retrieveAllPublication();
    void deletePublication(Integer id);
    List<Publication> retrievePublicationUserById (Long id);






}
