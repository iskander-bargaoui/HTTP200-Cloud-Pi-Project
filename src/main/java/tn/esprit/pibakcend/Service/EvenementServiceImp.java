package tn.esprit.pibakcend.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import tn.esprit.pibakcend.Repository.EvenementRepository;
import tn.esprit.pibakcend.entities.Evenement;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
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
    @Override
    public void rateEvent(Integer id, int value) {
        Evenement event = retrieveEvenementById(id);
        if (event == null) {
            throw new EntityNotFoundException();
        }
        event.rate(value);
        evenementRepository.save(event);
    }
    @Override
    public List<Object[]> getEventsCountbyId(Integer id) {
        // TODO Auto-generated method stub
        return evenementRepository.getEventsCountbyId(id);
    }
    @Override
    public HashMap<String, Object> eventpagination(Integer pageNo, Integer pageSize, String filter) {

        // TODO Auto-generated method stub
        PageRequest paging = PageRequest.of(pageNo, pageSize ,Sort.by("rating").descending());
        Page<Evenement> pagedResult = null;
        if(filter!=null && !filter.equals("")) {
            log.info("not null");
            pagedResult = evenementRepository.filterEvenementList(filter, paging);
        }else {
            pagedResult = evenementRepository.findAll(paging);
            log.info("null");
        }
        HashMap<String, Object> map = new HashMap<>();
        if(pagedResult.hasContent()) {
            map.put("event", pagedResult.getContent());
            map.put("total", evenementRepository.retrieveEvenementCount());
            map.put("page", pagedResult.getNumber());
            map.put("pageSize", pagedResult.getSize());
        } else {
            map.put("event", pagedResult.getContent());
            map.put("total", 0);
        }
        return map;
    }
}
