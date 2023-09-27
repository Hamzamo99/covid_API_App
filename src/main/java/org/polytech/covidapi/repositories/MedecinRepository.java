package org.polytech.covidapi.repositories;

import java.util.List;

import org.polytech.covidapi.modele.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedecinRepository extends JpaRepository<Medecin, Long>{
    
    //On definit une requête personnalisée pour récupérer les médecins associés à un centre par son ID
    @Query("SELECT m FROM Medecin m WHERE m.centre.id = :centreId")
    List<Medecin> findByCentreId(@Param("centreId") Long centreId);
    
}
