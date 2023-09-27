package org.polytech.covidapi.repositories;
import java.util.List;
import org.polytech.covidapi.modele.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    //On rajoute des méthodes ici pour gérer l'inscritption
    @Query("SELECT i FROM Inscription i WHERE i.centre.id = :centreId")
    List<Inscription> findByCentreId(@Param("centreId") Long centreId);

    @Query("SELECT i FROM Inscription i WHERE i.nom = :nom")
    List<Inscription> findByNom(@Param("nom") String nom);

}
