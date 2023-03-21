package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.repository.CommentaireRepository;

import java.util.List;

public class CommentaireServiceImp implements ICommentaire{
    CommentaireRepository commentaireRepository;
    @Override
    public Commentaire addComm(Commentaire comm) {
        return commentaireRepository.save(comm);
    }

    @Override
    public Commentaire updateComm(Commentaire comm) {
        return commentaireRepository.save(comm);
    }

    @Override
    public Commentaire retrieveCommentaireById(Integer id) {
        return commentaireRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commentaire> retrieveAllCommentaire() {
        return commentaireRepository.findAll();
    }

    @Override
    public void deleteCommentaire(Integer id) {
    commentaireRepository.deleteById(id);
    }
}
