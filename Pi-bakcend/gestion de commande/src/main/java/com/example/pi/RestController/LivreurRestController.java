package com.example.pi.RestController;

import com.example.pi.Service.LivreurService;
import com.example.pi.entities.Livreur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livreur")
//@CrossOrigin(origins="http://localhost:4200")
public class LivreurRestController {
    @Autowired
    private LivreurService livreurService;

    @GetMapping("/livreur/return")
    public Livreur getLivreur(@PathVariable Long id) {
        return livreurService.getLivreurById(id);
    }

    @PostMapping("/create")
    public Livreur createLivreur(@RequestBody Livreur livreur) {
        return livreurService.createLivreur(livreur);
    }

    @PutMapping("/update")
    public Livreur updateLivreur(@PathVariable Long id, @RequestBody Livreur livreur) {
        livreur.setId(id);
        return livreurService.updateLivreur(livreur);
    }
    @DeleteMapping("/delete")
    public void deleteLivreur(@PathVariable Long id) {
        livreurService.deleteLivreur(id);
    }

    @GetMapping("/getall")
    public List<Livreur> getAllLivreurs() {
        return livreurService.getAllLivreurs();
    }
}
