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
public class AdministrateurControlleur {
    
    
    private final InscriptionRepository inscriptionRepository;
    private MedecinRepository medecinRepository;
    private MedecinService medecinService;
    
    @Autowired
    public AdministrateurControlleur (InscriptionRepository inscriptionRepository,MedecinRepository medecinRepository,MedecinService medecinService) {
        this.inscriptionRepository = inscriptionRepository;
        this.medecinRepository = medecinRepository;
        this.medecinService = medecinService;
    }

    //(OK)
    @PostMapping("api/admin/administrateur/medecin")
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        // Code pour créer un nouveau médecin et le sauvegarder en base de données
        return medecinRepository.save(medecin);
    }

    //(OK)
    @GetMapping("api/admin/administrateur/medecin")
    public List<Medecin> getMedecinsByCentre(@RequestParam Long centreId) {
        // Code pour récupérer la liste des médecins associés au centre spécifié
        return medecinRepository.findByCentreId(centreId);
    }

    //(OK)
    @PutMapping("api/admin/administrateur/medecin/{id}")
    public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        return medecinService.updateMedecin(id, medecin);
    }

    //(OK)
    @GetMapping("api/admin/administrateur/inscription")
    public List<Inscription> getInscriptionsByCentre(@RequestParam Long centreId) {
        //Code pour récupérer la liste des réservations effectuées pour le centre spécifié
        return inscriptionRepository.findByCentreId(centreId);
    }
    
    //(OK)
    @DeleteMapping("api/admin/administrateur/inscription/{id}")
    public void deleteInscription(@PathVariable Long id) {
        //Code pour supprimer l'inscription avec l'ID spécifié
        inscriptionRepository.deleteById(id);
    }

}
