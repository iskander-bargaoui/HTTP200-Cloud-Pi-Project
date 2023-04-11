package com.example.pi.Service;

import com.example.pi.Repository.LivreurRepo;
import com.example.pi.entities.Livreur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class LivreurService implements Ilivreur{
    @Autowired
    private LivreurRepo livreurRepository;

    @Override
    public Livreur getLivreurById(Long id) {
        Optional<Livreur> livreurOptional = livreurRepository.findById(id);
        return livreurOptional.orElseThrow(() -> new EntityNotFoundException("Livreur not found with id: " + id));
    }

    @Override
    public Livreur createLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    @Override
    public Livreur updateLivreur(Livreur livreur) {
        getLivreurById(livreur.getId()); // check if livreur exists
        return livreurRepository.save(livreur);
    }

    @Override
    public void deleteLivreur(Long id) {
        livreurRepository.deleteById(id);
    }

    @Override
    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();

    }
}
