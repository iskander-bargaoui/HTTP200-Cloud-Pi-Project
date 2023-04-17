package com.example.pi.Service;

import com.example.pi.Repository.CommandeRepository;
import com.example.pi.entities.Commande;
import com.example.pi.entities.Countstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CommandeService  implements Icommande{
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private  PanierService panierService;
    public CommandeService(CommandeRepository commandeRepository,PanierService panierService) {
        this.commandeRepository = commandeRepository;
        this.panierService = panierService;
    }


    @Override
    public Commande createCommande(Commande commande) {
        //Command command = new Command();
        //command.setStatus(CommandeStatus.PENDING);
        //command.setProducts(panier.getProducts());
        return commandeRepository.save(commande);
    }

    @Override
    public Commande updateCommande(Long id, Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.getAllcommandsbydatecommande();
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commande> getCommandesByEtat(String etat) {
        return null;
    }

    @Override
    public List<Countstatus> getCountstatus() {
        return null;
    }
    //@Override
    //@GetMapping("/command/status")
    //public List<Countstatus> getCountstatus() {
        //return commandeRepository.getstatusbycommand();
    }

        //@Autowired
        //private CommandeRepository commandeRepository;

        //@Autowired
        //private PanierService panierService;

        //@Autowired
        //private NotificationService notificationService;

        //public void processCommande(Commande commande) {
        // Calculate total price of products in panier
        //double totalPrice = panierService.calculateTotalPrice(commande.getPanier());
        //commande.setTotalPrice(totalPrice);

        // Send panier to Notification dashboard for customer
        //notificationService.sendNotification(commande.getPanier());

        // Prompt customer to accept or reject the Commande
        //boolean isAccepted = promptCustomer(commande);

        //if (isAccepted) {
            // Prompt customer for delivery information and payment method
            //promptPaymentAndDelivery(commande);

            // Process payment
            //boolean isPaid = processPayment(commande.getTotalPrice(), commande.getPaymentMethod());

            //if (isPaid) {
                // Send notification to customer confirming payment
                //notificationService.sendPaymentNotification(commande);

                // Confirm delivery has been made
                //confirmDelivery(commande);

                // Save Commande to database
                //commandeRepository.save(commande);
            //} else {
                // Payment failed
                //throw new PaymentFailedException("Payment failed.");
           // }
       // } else {
            // Customer rejected Commande
            //panierService.resetPanier(commande.getPanier());
       // }
    //}

       // private boolean promptCustomer(Commande commande) {
        // Prompt customer to accept or reject Commande
        // Return true if accepted, false if rejected
        //return true; // placeholder
    //}

        ///private void promptPaymentAndDelivery(Commande commande) {
        // Prompt customer for payment method and delivery information
        // Set values in Commande object
        // ...
    //}

        //private boolean processPayment(double amount, PaymentMethod paymentMethod) {
        // Process payment and return true if successful, false if failed
       // return true; // placeholder
   // }

       // private void confirmDelivery(Commande)
    // {
        // Update Commande object to reflect delivery has been made
       // commande.setDeliveryStatus(DeliveryStatus.DELIVERED);
    //}




