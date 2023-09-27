package org.polytech.covidapi.services;
import org.polytech.covidapi.modele.Medecin;
import org.springframework.http.ResponseEntity;


public interface MedecinService {

    public ResponseEntity<?> validerVaccination(Long id, Long medecinId);
    ResponseEntity<Medecin> updateMedecin(Long id, Medecin medecin);
    
}
