package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.repository.CommentaireRepository;

import java.util.List;

@Service
@AllArgsConstructor
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
    public Commentaire retrieveCommentaireById(long id) {
        return commentaireRepository.findById(id).orElse(null);
    }


    @Override
    public List<Commentaire> retrieveAllCommentaire() {
        return commentaireRepository.findAll();
    }

    @Override
    public void deleteCommentaire(long id) {
    commentaireRepository.deleteById(id);
    }


}

