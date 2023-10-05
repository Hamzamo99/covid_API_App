package org.polytech.covidapi.controleurs;
import java.util.List;
import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.repositories.InscriptionRepository;
import org.polytech.covidapi.repositories.MedecinRepository;
import org.polytech.covidapi.services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedecinController {
    

    private final MedecinRepository medecinRepository;
    private final InscriptionRepository inscriptionRepository;
    private final MedecinService medecinService;

    @Autowired
    public MedecinController(MedecinRepository medecinRepository,InscriptionRepository inscriptionRepository,MedecinService medecinService){
        this.medecinRepository = medecinRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.medecinService = medecinService;
    }
   
    // Recherche d'une personne par son nom dans l'inscription (OK)
    @GetMapping("api/admin/medecin/rechercherPersonne")
    public List<Inscription> rechercherPersonneParNom(@RequestParam String nom) {
        return inscriptionRepository.findByNom(nom);
    }

    //(OK)
    @PutMapping("api/admin/medecin/validerVaccination/{id}")
    public ResponseEntity<?> validerVaccination(@PathVariable Long id, @RequestParam Long medecinId) {
        return medecinService.validerVaccination(id, medecinId);
    }

    //Effacement d'un medecin par l'Id (OK)
    @DeleteMapping("api/admin/medecins/{id}")
    public void deleteCentre(@PathVariable Long id) {
        medecinRepository.deleteById(id);
    }


}
