package org.polytech.covidapi.repositories;
import java.util.List;

import org.polytech.covidapi.modele.AdministrateurCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministrateurCentreRepository extends JpaRepository<AdministrateurCentre, Long> {
    @Query("SELECT a FROM AdministrateurCentre a WHERE a.centre.id = :centreId")
    List<AdministrateurCentre> findAdminsByCentreId(@Param("centreId") Long centreId);
}
