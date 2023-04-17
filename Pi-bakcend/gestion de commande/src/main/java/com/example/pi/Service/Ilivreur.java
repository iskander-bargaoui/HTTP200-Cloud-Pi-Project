package com.example.pi.Service;

import com.example.pi.entities.Livreur;

import java.util.List;

public interface Ilivreur {
    Livreur getLivreurById(Long id);

    Livreur createLivreur(Livreur livreur);

    Livreur updateLivreur(Livreur livreur);

    void deleteLivreur(Long id);

    List<Livreur> getAllLivreurs();
}
