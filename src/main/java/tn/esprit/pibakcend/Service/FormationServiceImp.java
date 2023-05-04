package tn.esprit.pibakcend.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.FormationRepository;
import tn.esprit.pibakcend.entities.Evenement;
import tn.esprit.pibakcend.entities.Formation;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service

public class FormationServiceImp implements IFormation{
    @Autowired
    FormationRepository formationRepository;

    @Override
    public Formation addFormation(Formation Fo) {
        return formationRepository.save(Fo);
    }

    @Override
    public Formation updateFormation(Formation Fo) {
        return formationRepository.save(Fo);
    }

    @Override
    public Formation retrieveFormationById(Integer id) {

        return formationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Formation> retrieveAllFormation() {


        return formationRepository.findAll() ;
    }

    @Override
    public void deleteFormation(Integer id) {
        formationRepository.deleteById(id);

    }

    /*
    @Override
    public void rateEvent(Integer id, int value) {
        Formation forma = retrieveFormationById(id);
        if (forma == null) {
            throw new EntityNotFoundException();
        }
        forma.rate(value);
        formationRepository.save(forma);
    }

     */
}
