package org.polytech.covidapi.repositories;
import org.polytech.covidapi.modele.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    @Query(value = "SELECT * FROM super_admin WHERE nom = :nom", nativeQuery = true)
    SuperAdmin findByNom(@Param("nom") String nom);
}

