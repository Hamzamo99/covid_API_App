package org.polytech.covidapi.controleurs;

import java.util.List;

import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.modele.Medecin;
import org.polytech.covidapi.repositories.InscriptionRepository;
import org.polytech.covidapi.repositories.MedecinRepository;
import org.polytech.covidapi.services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/admin/medecins")
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        // Code pour créer un nouveau médecin et le sauvegarder en base de données
        return medecinRepository.save(medecin);
    }

    @GetMapping("/admin/medecins")
    public List<Medecin> getMedecinsByCentre(@RequestParam Long centreId) {
        // Code pour récupérer la liste des médecins associés au centre spécifié
        return medecinRepository.findByCentreId(centreId);
    }

    @PutMapping("/admin/medecins/{id}")
    public Medecin updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        // Code pour mettre à jour les informations du médecin avec l'ID spécifié
        return medecinRepository.save(medecin);
    }

    // Recherche d'une personne par son nom dans l'inscription
    @GetMapping("admin/medecin/rechercherPersonne")
    public List<Inscription> rechercherPersonneParNom(@RequestParam String nom) {
        return inscriptionRepository.findByNom(nom);
    }

    // Valider la vaccination d'une personne par son Id d'inscription
    @PutMapping("admin/medecin/validerVaccination/{id}")
    public ResponseEntity<?> validerVaccination(@PathVariable Long id, @RequestBody Medecin medecin) {
        return medecinService.validerVaccination(id, medecin);
    }

}
