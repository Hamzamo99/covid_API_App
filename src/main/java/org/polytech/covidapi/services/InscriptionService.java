package org.polytech.covidapi.services;

import java.util.List;

import org.polytech.covidapi.modele.Inscription;

public interface InscriptionService {

    //Fonction qui permet de recuperer une liste d'inscriptions affactées à un centre 
    List<Inscription> rechercherInscriptionsParCentre(Long centreId);
    //Fonction pour créer une inscription
    Inscription creerInscription(Inscription inscription);
    
}
