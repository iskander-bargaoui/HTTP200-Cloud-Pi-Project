package tn.esprit.pibakcend.security.services;

import org.springframework.data.jpa.repository.Query;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ICommentaire {
   // Commentaire addComm(Commentaire comm);
    Commentaire updateComm(Commentaire comm,Integer idComm);

    List<Commentaire> retrieveCommentaireByPubId(Integer idPub);

    Commentaire assignCommentaireToPub (Commentaire comm, Integer idPub) throws IOException;

    void deleteCommentaire(Integer idComm) throws AccessDeniedException;

    Integer countByPublicationId(Integer idPub);

    List<Commentaire> commentsUser(Long idUser);

    //List<Commentaire> retrieveAllCommentaire();

    //List<Commentaire> retrieveCommentaireUserById (Long idUser);


    //Commentaire assignCommentaireToUser (Integer idComm, Long idUser);

    /*@Query("SELECT COUNT(comm) FROM Commentaire comm")
    Integer countCommentsByPublicationId(Integer idPub);*/

}
