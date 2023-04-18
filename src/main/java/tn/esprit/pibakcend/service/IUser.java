package tn.esprit.pibakcend.service;

import tn.esprit.pibakcend.entities.Publication;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface IUser {
    public User toggleFavoritePublication(Long idUser, Integer idPub);

    public List<Publication> getFavoritePublicationsByUserId(Long idUser);

    public void deleteExpiredFavoritePublications();



}
