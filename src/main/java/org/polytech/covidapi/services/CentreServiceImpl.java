package org.polytech.covidapi.services;
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
        return centreRepository.findByVille(ville);
    }

    @Override
    public Centre creerCentre(Centre centre) {
        return centreRepository.save(centre);
    }
}

