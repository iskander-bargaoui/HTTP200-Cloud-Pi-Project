package tn.esprit.pibakcend.security.services;

import tn.esprit.pibakcend.entities.Evenement;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;

public interface IEvenement {
    Evenement addEvenement(Evenement Ev);
    Evenement updateEvenement(Evenement Ev);
    Evenement retrieveEvenementById(Integer id);
    List<Evenement> retrieveAllEvenement();
    void deleteEvenement(Integer id) throws AccessDeniedException;
    //void rateEvent(Integer id, int value);
    HashMap<String,Object> eventpagination(Integer pageNo, Integer pageSize, String filter);
    List<Object[]> getEventsCountbyId(Integer id);
}
