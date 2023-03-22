package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.Evenement;

import java.util.List;

public interface IEvenement {
    Evenement addEvenement(Evenement Ev);
    Evenement updateEvenement(Evenement Ev);
    Evenement retrieveEvenementById(Integer id);
    List<Evenement> retrieveAllEvenement();
    void deleteEvenement(Integer id);
}
