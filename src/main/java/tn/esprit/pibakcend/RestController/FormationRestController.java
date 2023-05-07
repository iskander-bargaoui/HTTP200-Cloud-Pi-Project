package tn.esprit.pibakcend.RestController;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.security.services.IFormation;
import tn.esprit.pibakcend.entities.Formation;

import java.util.List;

@RestController
@RequestMapping("/api/formation/")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor

public class FormationRestController {
    @Autowired
    IFormation iFormation;

    @PostMapping("addFormation")
    public Formation addFormation(@RequestBody Formation Fo){

        return iFormation.addFormation(Fo);
    }

    @PutMapping("updateFormation")
    public Formation updateFormation(@RequestBody Formation Fo){
        return iFormation.updateFormation(Fo);
    }

    @GetMapping("getFormationbyid/{id}")
    public Formation retrieveFormationById(@PathVariable("id") Integer id){
        return iFormation.retrieveFormationById(id);
    }

    @GetMapping("listFormation")
    public List<Formation> retrieveAllFormation(){
        return iFormation.retrieveAllFormation();
    }

    @DeleteMapping("deleteFormation/{id}")
    public void deleteFormation(@PathVariable("id") Integer id){
        iFormation.deleteFormation(id);
    }

    /*
    @PostMapping("/Formation/{idFormation}/rate")
    public ResponseEntity<Void> rateEvent(@PathVariable Integer idFormation, @RequestParam int value) {
        iFormation.rateEvent(idFormation, value);
        return ResponseEntity.ok().build();
    }

     */

}
