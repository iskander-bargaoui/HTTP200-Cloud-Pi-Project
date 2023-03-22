package tn.esprit.pibakcend.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.IEvenement;
import tn.esprit.pibakcend.entities.Evenement;
import tn.esprit.pibakcend.entities.Formation;

import java.util.List;

@RestController
public class EvenementRestController {

    @Autowired
    IEvenement iEvenement;

    @PostMapping("addEvenement")
    public Evenement addEvenement(@RequestBody Evenement Fo){
        return iEvenement.addEvenement(Fo);
    }

    @PutMapping("updateEvenement")
    public Evenement updateEvenement(@RequestBody Evenement Fo){
        return iEvenement.updateEvenement(Fo);
    }

    @GetMapping("getEvenementbyid/{id}")
    public Evenement retrieveEvenementById(@PathVariable("id") Integer id){
        return iEvenement.retrieveEvenementById(id);
    }

    @GetMapping("listEvenement")
    public List<Evenement> retrieveAllEvenement(){
        return iEvenement.retrieveAllEvenement();
    }

    @DeleteMapping("deleteEvenement/{id}")
    public void deleteEvenement(@PathVariable("id") Integer id){
        iEvenement.deleteEvenement(id);
    }
}
