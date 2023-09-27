package org.polytech.covidapi.repositories;
import java.util.List;
import org.polytech.covidapi.modele.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CentreRepository extends JpaRepository<Centre, Long> {

    //On rajoute ici des méthodes
    @Query("SELECT c FROM Centre c WHERE c.ville = :ville")
    List<Centre> findByVille(@Param("ville") String ville);
    
}
