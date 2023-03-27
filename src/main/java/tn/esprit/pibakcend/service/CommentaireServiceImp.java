package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.repository.CommentaireRepository;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentaireServiceImp implements ICommentaire{
    CommentaireRepository commentaireRepository;

    UserRepository userRepository;

    PublicationRepository publicationRepository;
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

    @Override
    public Commentaire assignCommentaireToUser(Integer idComm, Long idUser) {
        Commentaire commentaire = commentaireRepository.findById(idComm).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);

        user.getCommentaires().add(commentaire);
        return commentaireRepository.save(commentaire);

    }

    @Override
    public Commentaire assignCommentaireToPub(Integer idComm, Integer idPub) {
        Commentaire commentaire = commentaireRepository.findById(idComm).orElse(null);
        Publication publication = publicationRepository.findById(idPub).orElse(null);

        publication.getCommentaires().add(commentaire);
        return commentaireRepository.save(commentaire);
    }


}

