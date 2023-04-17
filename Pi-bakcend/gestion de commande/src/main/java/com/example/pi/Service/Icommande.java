package com.example.pi.Service;

import com.example.pi.entities.Commande;
import com.example.pi.entities.Countstatus;

import java.util.List;

public interface Icommande {
        Commande createCommande(Commande commande);

        Commande updateCommande(Long id, Commande commande);

        void deleteCommande(Long id);

        List<Commande> getAllCommandes();

        Commande getCommandeById(Long id);

        List<Commande> getCommandesByEtat(String etat);


        List<Countstatus> getCountstatus();
}



