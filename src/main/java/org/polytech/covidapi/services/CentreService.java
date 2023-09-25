package org.polytech.covidapi.services;
import java.util.List;

import org.polytech.covidapi.modele.Centre;

public interface CentreService {
    List<Centre> rechercherCentresParVille(String ville);
    Centre creerCentre(Centre centre);
}

