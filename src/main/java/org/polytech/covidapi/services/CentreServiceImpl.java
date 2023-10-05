package org.polytech.covidapi.services;
import java.util.Collections;
import java.util.List;
import org.polytech.covidapi.modele.Centre;
import org.polytech.covidapi.repositories.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentreServiceImpl implements CentreService {

    private final CentreRepository centreRepository;

    @Autowired
    public CentreServiceImpl(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
    }

    @Override
    public List<Centre> rechercherCentresParVille(String ville) {
        if (ville != null && !ville.isEmpty()) {
            return centreRepository.findByVilleIgnoreCase(ville);
        } else {
            // Gérer le cas où ville est vide ou null
            // Par exemple, renvoyer un message d'erreur ou ne rien faire
            return Collections.emptyList(); // Renvoie une liste vide lorsque ville est vide ou null
        }
    }
    

    @Override
    public Centre creerCentre(Centre centre) {
        return centreRepository.save(centre);
    }
}

