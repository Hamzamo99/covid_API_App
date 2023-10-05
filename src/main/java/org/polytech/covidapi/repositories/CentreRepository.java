package org.polytech.covidapi.repositories;
import java.util.List;
import org.polytech.covidapi.modele.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CentreRepository extends JpaRepository<Centre, Long> {

    @Query(value = "SELECT * FROM centre c WHERE c.ville ILIKE %:ville%", nativeQuery = true)
    List<Centre> findByVilleIgnoreCase(@Param("ville") String ville);
    
}
