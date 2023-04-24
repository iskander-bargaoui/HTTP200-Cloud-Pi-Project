package tn.esprit.pibakcend.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.IFormation;
import tn.esprit.pibakcend.entities.Formation;

import java.util.List;

@RestController
public class FormationRestController {
    @Autowired
    IFormation iFormation;

    @PostMapping("addFormation")
    public Formation addFormation(@RequestBody Formation Fo){

        return iFormation.addFormation(Fo);
    }

    @PutMapping("updateFormation")
    public Formation updatePiste(@RequestBody Formation Fo){
        return iFormation.updateFormation(Fo);
    }

    @GetMapping("getFormationbyid/{id}")
    public Formation retrieveFormationById(@PathVariable("id") Integer id){
        return iFormation.retrieveFormationById(id);
    }

    @GetMapping("listFormation")
    public List<Formation> retrieveAllPiste(){
        return iFormation.retrieveAllFormation();
    }

    @DeleteMapping("deleteFormation/{id}")
    public void deleteFormation(@PathVariable("id") Integer id){
        iFormation.deleteFormation(id);
    }

    @PostMapping("/Formation/{idFormation}/rate")
    public ResponseEntity<Void> rateEvent(@PathVariable Integer idFormation, @RequestParam int value) {
        iFormation.rateEvent(idFormation, value);
        return ResponseEntity.ok().build();
    }

   /* @PostMapping("/Formation/assignFormationToEvenement")
    public void assignFormationsToEvenements(@RequestParam List<Integer> formationIds, @RequestParam List<Integer> evenementIds){
        iFormation.assignFormationsToEvenements(formationIds, evenementIds);
    }*/

}
