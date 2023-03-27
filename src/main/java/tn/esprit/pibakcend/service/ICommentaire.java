package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.Commentaire;

import java.util.List;

public interface ICommentaire {
    Commentaire addComm(Commentaire comm);
    Commentaire updateComm(Commentaire comm);

    Commentaire retrieveCommentaireById(Integer id );

    List<Commentaire> retrieveAllCommentaire();

    void deleteCommentaire(Integer id);

    Commentaire assignCommentaireToUser (Integer idComm, Long idUser);

    Commentaire assignCommentaireToPub (Integer idComm, Integer idPub);


}
