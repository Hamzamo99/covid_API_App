package org.polytech.covidapi.modele;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Centre {

    @Id
    //Generation automatique des identifiants geré par la base de données
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nom;
    private String adresse;
    private String ville;
    
    //Chaque centre peut avoir plusieurs inscriptions
    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Centre gère la relation
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Centre gère la relation
    private List<AdministrateurCentre> administrateurs;

    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Centre gère la relation
    private List<Medecin> medecins;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public List<AdministrateurCentre> getAdministrateurs() {
        return administrateurs;
    }

    public void setAdministrateurs(List<AdministrateurCentre> administrateurs) {
        this.administrateurs = administrateurs;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

   
    

}
