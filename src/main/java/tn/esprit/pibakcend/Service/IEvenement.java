package tn.esprit.pibakcend.Service;

import tn.esprit.pibakcend.entities.Evenement;
import java.util.HashMap;

import java.util.List;

public interface IEvenement {
    Evenement addEvenement(Evenement Ev);
    Evenement updateEvenement(Evenement Ev);
    Evenement retrieveEvenementById(Integer id);
    List<Evenement> retrieveAllEvenement();
    void deleteEvenement(Integer id);
    //void rateEvent(Integer id, int value);
    HashMap<String,Object> eventpagination(Integer pageNo, Integer pageSize, String filter);
    List<Object[]> getEventsCountbyId(Integer id);
}
