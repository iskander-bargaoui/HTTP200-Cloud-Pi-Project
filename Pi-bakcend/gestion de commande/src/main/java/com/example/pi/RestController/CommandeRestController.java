package com.example.pi.RestController;

import com.example.pi.Service.CommandeService;
import com.example.pi.Service.PanierService;
import com.example.pi.entities.Commande;
import com.example.pi.entities.Countstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins="http://localhost:4200")
public class CommandeRestController {

        @Autowired
        private CommandeService commandeService;

        @GetMapping("/getallcommandes")
        public List<Commande> getAllCommandes() {
            return commandeService.getAllCommandes();
        }

        @GetMapping("/getcommandes/{id}")
        public Commande getCommandeById(@PathVariable Long id) {

            return commandeService.getCommandeById(id);
        }

        @PostMapping
        public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
            Commande createdCommande = commandeService.createCommande(commande);
            return new ResponseEntity<>(createdCommande, HttpStatus.CREATED);
        }

    @PutMapping("/updatecommandes/{id}") // add the path variable for id
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        Commande updatedCommande = commandeService.updateCommande(id, commande);
        return new ResponseEntity<>(updatedCommande, HttpStatus.OK);
        }

        @DeleteMapping("/deletecommandes")
        public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
            commandeService.deleteCommande(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //e5er modif
        //@GetMapping("/command/CNstatus")
        //List<Countstatus> getcommandstatus(){
            //return commandeService.getCountstatus();
        //}
    //@GetMapping("/{panierId}/calculate-total-price")
    //public void calculateTotalPrice(@PathVariable Long panierId) {
        //PanierService.calculateTotalPrice(panierId);
    //}

    //@PostMapping
   // public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
       // commandeService.processCommande(commande);
       // return new ResponseEntity<>(commande, HttpStatus.CREATED);
    //}
    }

