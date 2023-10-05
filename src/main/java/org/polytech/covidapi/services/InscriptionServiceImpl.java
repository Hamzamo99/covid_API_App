package org.polytech.covidapi.services;
import java.util.List;
import org.polytech.covidapi.modele.Centre;
import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.repositories.CentreRepository;
import org.polytech.covidapi.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscriptionServiceImpl implements InscriptionService{
    
    private final InscriptionRepository inscriptionRepository;
    private final CentreRepository centreRepository;
    

    @Autowired
    public InscriptionServiceImpl(InscriptionRepository inscriptionRepository, CentreRepository centreRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.centreRepository = centreRepository;
      
    }

    @Override
    public List<Inscription> rechercherInscriptionsParCentre(Long centreId) {
        Centre centre = centreRepository.findById(centreId).orElse(null);
        if (centre != null) {
            return centre.getInscriptions();
        }
        return null;
    }

    public Inscription creerInscription(Inscription inscription) {

        //On récupere l'ID du centre de l'inscription
        Long centreId = inscription.getCentre().getId();

        //On vérifie si l'ID du centre est valide
        if (centreId == null) {
            throw new IllegalArgumentException("L'inscription doit être associée à un centre valide");
        }

        //On recherche le centre par son ID
        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new IllegalArgumentException("Centre introuvable avec l'ID : " + centreId));

        //On associe le centre récupéré à l'objet Inscription
        inscription.setCentre(centre);

        //On enregistre l'inscription
        Inscription savedInscription = inscriptionRepository.save(inscription);

        //On renvoie l'inscription enregistrée
        return savedInscription;
    }


}
