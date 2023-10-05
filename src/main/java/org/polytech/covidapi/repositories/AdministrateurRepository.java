package org.polytech.covidapi.repositories;
import java.util.List;
import org.polytech.covidapi.modele.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    @Query("SELECT a FROM Administrateur a WHERE a.centre.id = :centreId")
    List<Administrateur> findAdminsByCentreId(@Param("centreId") Long centreId);
}
