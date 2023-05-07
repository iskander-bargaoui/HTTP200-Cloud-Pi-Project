package tn.esprit.pibakcend.security.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.EvenementRepository;
import tn.esprit.pibakcend.Repository.UserRepository;
import tn.esprit.pibakcend.entities.Evenement;
import tn.esprit.pibakcend.entities.User;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class EvenementServiceImp implements  IEvenement{
    EvenementRepository evenementRepository;
    UserRepository userRepository;

    public Evenement addEvenement(Evenement Ev) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Ev.setUserr(user);
        return evenementRepository.save(Ev);
    }

    @Override
    public Evenement updateEvenement(Evenement Ev) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Ev.setUserr(user);
        return evenementRepository.save(Ev);
    }

    @Override
    public Evenement retrieveEvenementById(Integer id) {

        return evenementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Evenement> retrieveAllEvenement() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return evenementRepository.findAllByUserr(user);
    }

    @Override
    public void deleteEvenement(Integer id) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Evenement> evenementOptional = evenementRepository.findById(id);
        if (evenementOptional.isPresent()) {
            Evenement evenement = evenementOptional.get();
            if (evenement.getUserr().equals(user)) {
                evenementRepository.deleteById(id);
            } else {
                throw new AccessDeniedException("You do not have permission to delete this event");
            }
        } else {
            throw new EntityNotFoundException("Event with id " + id + " not found");
        }


    }
    /*
    @Override
    public void rateEvent(Integer id, int value) {
        Evenement event = retrieveEvenementById(id);
        if (event == null) {
            throw new EntityNotFoundException();
        }
        event.rate(value);
        evenementRepository.save(event);
    }

     */
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
