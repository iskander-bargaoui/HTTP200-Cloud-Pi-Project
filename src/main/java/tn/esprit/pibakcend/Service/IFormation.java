package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.Evenement;
import tn.esprit.pibakcend.entities.Formation;

import java.util.List;

public interface IFormation {

    Formation addFormation(Formation Fo);
    Formation updateFormation(Formation Fo);
    Formation retrieveFormationById(Integer id);
    List<Formation> retrieveAllFormation();
    void deleteFormation(Integer id);
    void rateEvent(Integer id, int value);

    //void assignFormationToEvenement(Integer idFormation, Integer idEvenement);

    //void assignFormationsToEvenements(List<Integer> formationIds, List<Integer> evenementIds);
}
