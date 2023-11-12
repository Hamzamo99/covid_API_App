package org.polytech.covidapi.controleurs;
import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("api/public/inscriptions")
    public Inscription creerInscription(@RequestBody Inscription inscription) {
        //On appelle le service pour créer l'inscription
        return inscriptionService.creerInscription(inscription);
    }
    
}