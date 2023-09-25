package org.polytech.covidapi.services;

import org.polytech.covidapi.modele.Medecin;
import org.springframework.http.ResponseEntity;


public interface MedecinService {
    public ResponseEntity<?> validerVaccination(Long id, Medecin medecin);
}
