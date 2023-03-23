package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.Commentaire;

import java.util.List;

public interface ICommentaire {
    Commentaire addComm(Commentaire comm);
    Commentaire updateComm(Commentaire comm);

    Commentaire retrieveCommentaireById(long id );

    List<Commentaire> retrieveAllCommentaire();

    void deleteCommentaire(long id);
}
