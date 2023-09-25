package org.polytech.covidapi.services;

import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.modele.Medecin;
import org.polytech.covidapi.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class MedecinServiceImpl implements MedecinService {

    private final InscriptionRepository inscriptionRepository;

    @Autowired
    public MedecinServiceImpl(InscriptionRepository inscriptionRepository){
        this.inscriptionRepository = inscriptionRepository;
    }
    
    @Override
    public ResponseEntity<?> validerVaccination(Long id, Medecin medecin) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personne introuvable avec l'ID : " + id));
        
        // Valider la vaccination de la personne en lui associant un medecin
        inscription.setVaccinePass(medecin);
        
        inscriptionRepository.save(inscription); // On enregistre les modifications de l'inscription de la personne
        
        return ResponseEntity.ok("Vaccination validée avec succès pour la personne avec l'ID : " + id);
    }
    
}
