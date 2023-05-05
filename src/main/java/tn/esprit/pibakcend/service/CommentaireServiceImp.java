package tn.esprit.pibakcend.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.Commentaire;
import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.repository.CommentaireRepository;
import tn.esprit.pibakcend.repository.PublicationRepository;
import tn.esprit.pibakcend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentaireServiceImp implements ICommentaire{
    CommentaireRepository commentaireRepository;

    UserRepository userRepository;

    PublicationRepository publicationRepository;

    private TwillioSmsSender twillioSmsSender;

    @Override
    public Commentaire updateComm(Commentaire comm,Integer idComm) {
        Commentaire commentaire = commentaireRepository.findById(idComm).orElse(null);
        comm.setIdComm(idComm);
        comm.setUser(commentaire.getUser());
        comm.setPublication(commentaire.getPublication());
        return commentaireRepository.save(comm);
    }

    @Override
    public List<Commentaire> retrieveCommentaireByPubId(Integer idPub) {
        return commentaireRepository.findAll().stream().filter(x -> x.getPublication().getIdPub() == idPub).collect(Collectors.toList());
    }


    @Override
    public Commentaire assignCommentaireToPub(Commentaire comm, Integer idPub, Long idUser) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        if (publication != null && user !=null){
            comm.setPublication(publication);
            comm.setUser(user);
            commentaireRepository.save(comm);
            // Send SMS notification to the user who created the publication
           /* User pubUser = publication.getUser();
            if (pubUser != null) {
                String message = String.format("%s commented on your publication '%s'", user.getUsername(), publication.getTitrePub());
                SmsRequest smsRequest = new SmsRequest(pubUser.getTelNumber(), message);
                twillioSmsSender.sendSms(smsRequest);
            }*/
            return comm;
        }

        return null;

    }

    @Override
    public void deleteCommentaire(Integer idComm) {
    commentaireRepository.deleteById(idComm);
    }

    @Override
    public Integer countByPublicationId(Integer idPub) {
        return commentaireRepository.countByPublicationId(idPub);
    }


}

