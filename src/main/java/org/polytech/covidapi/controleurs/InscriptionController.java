package org.polytech.covidapi.controleurs;
import java.util.List;
import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @Autowired
    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @GetMapping("/public/inscriptions")
    public List<Inscription> rechercherInscriptionsParCentre(@PathVariable Long centreId) {
        return inscriptionService.rechercherInscriptionsParCentre(centreId);
    }

    @PostMapping("/public/inscriptions")
    public Inscription creerInscription(@RequestBody Inscription inscription) {
        //On appelle le service pour créer l'inscription
        return inscriptionService.creerInscription(inscription);
    }
    
}