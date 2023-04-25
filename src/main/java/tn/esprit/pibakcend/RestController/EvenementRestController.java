package tn.esprit.pibakcend.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Repository.EvenementRepository;
import tn.esprit.pibakcend.Service.EvenementServiceImp;
import tn.esprit.pibakcend.Service.IEvenement;
import tn.esprit.pibakcend.Service.PdfGeneratorService;
import tn.esprit.pibakcend.entities.Evenement;
import tn.esprit.pibakcend.entities.Formation;
import java.util.HashMap;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import java.nio.file.Files;


@RestController
public class EvenementRestController {

    @Autowired
    IEvenement iEvenement;
    @Autowired
    EvenementServiceImp evenementServiceImp;
    @Autowired
    private PdfGeneratorService pdfGeneratorService;

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
/*
    @GetMapping("listEvenement")
    public List<Evenement> retrieveAllEvenement(){
        return iEvenement.retrieveAllEvenement();
    }
*/
    @GetMapping("/retrieve-event")
    public ResponseEntity<HashMap<String, Object>> getEventt(
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "8") Integer pageSize,
        @RequestParam(required = false) String filter) {

    HashMap<String, Object> response = new HashMap<>();


    response.put("data", evenementServiceImp.eventpagination(pageNo, pageSize, filter));
    response.put("status", "success");
    response.put("message", "List of Event retrieved successfully");


    return ResponseEntity.ok(response);
}

    @GetMapping("/count/{idEvenement}")
    public ResponseEntity<List<Object[]>> getEventCountById(@PathVariable("idEvenement") Integer idEvenement) {
        List<Object[]> livraisonsCount = evenementServiceImp.getEventsCountbyId(idEvenement);
        return new ResponseEntity<>(livraisonsCount, HttpStatus.OK);
    }




    ////////////

    @DeleteMapping("deleteEvenement/{id}")
    public void deleteEvenement(@PathVariable("id") Integer id){
        iEvenement.deleteEvenement(id);
    }

    /*
    @PostMapping("/Evenement/{idEvenement}/rate")
    public ResponseEntity<Void> rateEvent(@PathVariable Integer idEvenement,
                                          @RequestParam int value) {
        Evenement event = IEvenement.retrieveEvenementById(idEvenement).orElseThrow(EntityNotFoundException::new);
        event.rate(value);
        eventRepository.save(event);
        return ResponseEntity.ok().build();
    }

     */

    @PostMapping("/Evenement/{idEvenement}/rate")
    public ResponseEntity<Void> rateEvent(@PathVariable Integer idEvenement, @RequestParam int value) {
        iEvenement.rateEvent(idEvenement, value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/events/pdf")
    public ResponseEntity<byte[]> generateEventsPDF() {
        // Get events from the database
        List<Evenement> events = iEvenement.retrieveAllEvenement();


        pdfGeneratorService.generatePDF(events);


        Path file = Paths.get("events_list.pdf");
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(file);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        // Return the PDF file as a ResponseEntity
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName().toString() + "\"")
                .body(fileContent);
    }



}
