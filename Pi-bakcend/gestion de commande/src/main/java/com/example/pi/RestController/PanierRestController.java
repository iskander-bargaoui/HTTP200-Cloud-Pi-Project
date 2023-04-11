package com.example.pi.RestController;

import com.example.pi.Service.PanierService;
import com.example.pi.entities.Panier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/paniers")
@CrossOrigin(origins="http://localhost:4200")
public class PanierRestController {
    @Autowired
    private PanierService panierService;

    @PostMapping("/paniers")
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier) {
        // This endpoint creates a new panier by calling the corresponding service method
        Panier createdPanier = panierService.createPanier(panier);
        return new ResponseEntity<>(createdPanier, HttpStatus.CREATED);
    }

    @GetMapping("/services")
    public ResponseEntity<List<Panier>> getAllPaniers() {
        // This endpoint retrieves all paniers by calling the corresponding service method
        List<Panier> paniers = panierService.getAllPaniers();
        return new ResponseEntity<>(paniers, HttpStatus.OK);
    }

    @GetMapping("/getpaniers/{id}")
    public ResponseEntity<Panier> getPanierById(@PathVariable Long id) {
        // This endpoint retrieves a panier by its id by calling the corresponding service method
        Panier panier = panierService.getPanierById(id);
        return new ResponseEntity<>(panier, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Panier> updatePanier(@PathVariable Long id, @RequestBody Panier panier) {
        // This endpoint updates a panier by calling the corresponding service method
        panier.setId(id);
        Panier updatedPanier = panierService.updatePanier(panier);
        return new ResponseEntity<>(updatedPanier, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePanierById(@PathVariable Long id) {
        // This endpoint deletes a panier by its id by calling the corresponding service method
        panierService.deletePanierById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/reset/panier")
    public ResponseEntity<Void> resetPanier(@PathVariable Long id) {
        Panier panier = panierService.getPanierById(id);
        if (panier == null) {
            return ResponseEntity.notFound().build();
        }
        panierService.resetPanier(panier);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/assign-commande")
    public ResponseEntity<Panier> assignCommandeToPanier(@PathVariable Long idCommande, @PathVariable Long idUser) {
        Panier panier = panierService.assignCommandeToPanier(idCommande, idUser);
        return ResponseEntity.ok(panier);
    }
    //@GetMapping("/{panierId}/calculate-total-price")
    //public void calculateTotalPrice(@PathVariable Long panierId) {
        //panierService.calculateTotalPrice(panierId);
    //}

    //@PostMapping("/send-to-dashboard")
    //public void sendPanierToDashboard(@RequestBody Panier panier) {
        //panierService.sendPanierToDashboard(panier);
    //}
    //@PostMapping("/proceed-to-payment")
    //public void proceedToPayment(@RequestBody Panier panier) {
        //panierService.proceedToPayment(panier);
   // }
}
