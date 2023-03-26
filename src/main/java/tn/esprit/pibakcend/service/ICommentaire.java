package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Like;

import java.util.List;

public interface ICommentaire {
    Commentaire addComm(Commentaire comm);
    Commentaire updateComm(Commentaire comm);

    Commentaire retrieveCommentaireById(Integer id );

    List<Commentaire> retrieveAllCommentaire();

    void deleteCommentaire(Integer id);

}
