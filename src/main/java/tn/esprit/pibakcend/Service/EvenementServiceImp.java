package tn.esprit.pibakcend.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.EvenementRepository;
import tn.esprit.pibakcend.entities.Evenement;

import java.util.List;

@Slf4j
@Service
public class EvenementServiceImp implements  IEvenement{
    @Autowired
    EvenementRepository evenementRepository;

    @Override
    public Evenement addEvenement(Evenement Ev) {
        return evenementRepository.save(Ev);
    }

    @Override
    public Evenement updateEvenement(Evenement Ev) {
        return evenementRepository.save(Ev);
    }

    @Override
    public Evenement retrieveEvenementById(Integer id) {
        return evenementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Evenement> retrieveAllEvenement() {
        return evenementRepository.findAll();
    }

    @Override
    public void deleteEvenement(Integer id) {
        evenementRepository.deleteById(id);

    }
}
