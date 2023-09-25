package org.polytech.covidapi.repositories;

import org.polytech.covidapi.modele.AdministrateurCentre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurCentreRepository extends JpaRepository<AdministrateurCentre, Long> {
    
}
