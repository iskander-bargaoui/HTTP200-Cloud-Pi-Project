package tn.esprit.pibakcend.service;

import org.springframework.data.jpa.repository.Query;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;

import java.util.List;

public interface ICommentaire {
    Commentaire addComm(Commentaire comm);
    Commentaire updateComm(Commentaire comm);

    Commentaire retrieveCommentaireById(Integer idComm );

    List<Commentaire> retrieveAllCommentaire();

    void deleteCommentaire(Integer idComm);

    List<Commentaire> retrieveCommentaireUserById (Long idUser);


    Commentaire assignCommentaireToUser (Integer idComm, Long idUser);

    Commentaire assignCommentaireToPub (Integer idComm, Integer idPub);

    /*@Query("SELECT COUNT(comm) FROM Commentaire comm")
    Integer countCommentsByPublicationId(Integer idPub);*/

    Integer countByPublicationId(Integer idPub);
}
