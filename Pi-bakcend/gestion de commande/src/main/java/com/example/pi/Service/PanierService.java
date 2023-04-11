package com.example.pi.Service;

import com.example.pi.Repository.CommandeRepository;
import com.example.pi.Repository.PanierRepository;
import com.example.pi.entities.Commande;
import com.example.pi.entities.Panier;
import com.example.pi.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierService implements Ipanier {
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Override
    // This method creates a new panier by saving it in the database
    public Panier createPanier(Panier panier) {
        Panier panier1 = new Panier();
        return panierRepository.save(panier);
    }

    @Override
    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    @Override
    public Panier getPanierById(Long id) {
        Optional<Panier> optionalPanier = panierRepository.findById(id);
        return optionalPanier.orElse(null);
    }

    @Override
    public void deletePanierById(Long id) {
        // This method deletes a panier by its id from the database
        Panier panier = getPanierById(id);
        panierRepository.delete(panier);
    }

    @Override
    public Panier updatePanier(Panier panier) {
        //his method updates a panier by saving the changes in the database
        return panierRepository.save(panier);
    }

    @Override
    public void resetPanier(Panier panier) {
        panierRepository.save(panier);

    }
    //public void addProductToPanier(Long panierId, Long productId, Integer quantity) {
        //Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier not found"));
        //Produit product = pro.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        //for (int i = 0; i < quantity; i++) {
            //panier.addproduct(product);
        //}

        //panierRepository.save(panier);
    //}

    public Panier updatePanier(Long idPanier, Panier panier) {

        return panierRepository.save(panier);
    }

    //assign a Commande (order) to a Panier (shopping cart) for a given user, based on their IDs.
    public Panier assignCommandeToPanier(Long idCommande, Long idUser) {
        Panier panier = panierRepository.findByUserId(idUser);
        Commande commande = commandeRepository.findById(idCommande).get();
        System.out.println(panier);
    return panier;

    }

    //public void calculateTotalPrice(Long panierId) {
        //Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier not found"));

        //double totalPrice = 0;
        //for (PanierItem panierItem : panier.getPanierItems()) {
            //totalPrice += panierItem.getProduct().getPrice() * panierItem.getQuantity();
        //}

        //panier.setTotalPrice(totalPrice);
        //panierRepository.save(panier);


        //public void sendPanierToDashboard(Panier panier) {
            //notificationService.sendNotification("Panier Notification", "New Panier added", panier);
        //}

    //public boolean acceptOrRejectCommande() {
        // prompt the customer to accept or reject the Commande
        // return true if accepted, false if rejected
    }

    //public void resetPanier(Panier panier) {
       // panier.clearPanier();
      //  panier.setTotalPrice(0);
  //  }

   // public void proceedToPayment(Panier panier) {
     //   if (acceptOrRejectCommande()) {
           // String deliveryInformation = // prompt the customer to provide delivery information
                  //  String paymentMethod = // prompt the customer to select a payment method

                  //  Commande commande = new Commande(panier, deliveryInformation, paymentMethod);
         //   commandeService.save(commande);

            // redirect the customer to the payment gateway
       // } else {
         //   resetPanier(panier);
      //  }
  //  }
   // }

