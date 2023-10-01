package org.polytech.covidapi.controleurs;
import java.util.List;
import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.modele.Medecin;
import org.polytech.covidapi.repositories.InscriptionRepository;
import org.polytech.covidapi.repositories.MedecinRepository;
import org.polytech.covidapi.services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("api/admin/medecins")
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        // Code pour créer un nouveau médecin et le sauvegarder en base de données
        return medecinRepository.save(medecin);
    }

    @GetMapping("api/admin/medecins")
    public List<Medecin> getMedecinsByCentre(@RequestParam Long centreId) {
        // Code pour récupérer la liste des médecins associés au centre spécifié
        return medecinRepository.findByCentreId(centreId);
    }

    @PutMapping("api/admin/medecins/{id}")
    public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        return medecinService.updateMedecin(id, medecin);
    }

    // Recherche d'une personne par son nom dans l'inscription
    @GetMapping("api/admin/medecin/rechercherPersonne")
    public List<Inscription> rechercherPersonneParNom(@RequestParam String nom) {
        return inscriptionRepository.findByNom(nom);
    }

    @PutMapping("api/admin/medecin/validerVaccination/{id}")
    public ResponseEntity<?> validerVaccination(@PathVariable Long id, @RequestParam Long medecinId) {
        return medecinService.validerVaccination(id, medecinId);
    }

    //Effacement d'un medecin par l'Id
    @DeleteMapping("api/admin/medecins/{id}")
    public void deleteCentre(@PathVariable Long id) {
        medecinRepository.deleteById(id);
    }


}
