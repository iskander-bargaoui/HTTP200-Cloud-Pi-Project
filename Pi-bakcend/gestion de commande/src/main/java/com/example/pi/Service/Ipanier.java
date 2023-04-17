package com.example.pi.Service;

import com.example.pi.entities.Panier;

import java.util.List;

public interface Ipanier {


        Panier createPanier(Panier panier);

        List<Panier> getAllPaniers();

        Panier getPanierById(Long id);

        void deletePanierById(Long id);

        Panier updatePanier(Panier panier);
        void resetPanier(Panier panier);
    }

