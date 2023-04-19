package tn.esprit.pibakcend.Service;

import org.springframework.data.jpa.repository.Query;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;

import java.util.List;

public interface ICommentaire {
   // Commentaire addComm(Commentaire comm);
    Commentaire updateComm(Commentaire comm,Integer idComm);

    List<Commentaire> retrieveCommentaireByPubId(Integer idPub);

    Commentaire assignCommentaireToPub (Commentaire comm, Integer idPub, Long idUser);

    void deleteCommentaire(Integer idComm);

    Integer countByPublicationId(Integer idPub);

    //List<Commentaire> retrieveAllCommentaire();

    //List<Commentaire> retrieveCommentaireUserById (Long idUser);


    //Commentaire assignCommentaireToUser (Integer idComm, Long idUser);

    /*@Query("SELECT COUNT(comm) FROM Commentaire comm")
    Integer countCommentsByPublicationId(Integer idPub);*/

}
