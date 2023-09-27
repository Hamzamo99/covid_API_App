package org.polytech.covidapi.services;
import java.util.Optional;
import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.modele.Medecin;
import org.polytech.covidapi.repositories.InscriptionRepository;
import org.polytech.covidapi.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedecinServiceImpl implements MedecinService {

    private final InscriptionRepository inscriptionRepository;
    private final MedecinRepository medecinRepository;

    @Autowired
    public MedecinServiceImpl(InscriptionRepository inscriptionRepository,MedecinRepository medecinRepository){
        this.inscriptionRepository = inscriptionRepository;
        this.medecinRepository = medecinRepository;
    }

    @Override
    public ResponseEntity<?> validerVaccination(Long id, Long medecinId) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personne introuvable avec l'ID : " + id));
        
        //On recherche le médecin par son ID
        Medecin medecin = medecinRepository.findById(medecinId)
                .orElseThrow(() -> new IllegalArgumentException("Médecin introuvable avec l'ID : " + medecinId));

        //On associe le médecin à l'inscription
        inscription.setVaccinePass(medecin);

        //On enregistre les modifications de l'inscription de la personne
        inscriptionRepository.save(inscription);

        return ResponseEntity.ok("Vaccination validée avec succès pour la personne avec l'ID : " + id);
    }

    @Override
    public ResponseEntity<Medecin> updateMedecin(Long id, Medecin medecin) {

        Optional<Medecin> medecinTrouveOptional = medecinRepository.findById(id);

        if (medecinTrouveOptional.isPresent()) {
            Medecin medecinTrouve = medecinTrouveOptional.get();

            // Mettez à jour les champs du médecin avec les nouvelles valeurs
            medecinTrouve.setNom(medecin.getNom());
            medecinTrouve.setPrenom(medecin.getPrenom());
            medecinTrouve.setEmail(medecin.getEmail());
            medecinTrouve.setTelephone(medecin.getTelephone());
            medecinTrouve.setCentre(medecin.getCentre());

            // Sauvegardez les modifications dans la base de données
            Medecin updatedMedecin = medecinRepository.save(medecinTrouve);

            return ResponseEntity.ok(updatedMedecin);
        } else {
            // Si le médecin avec l'ID spécifié n'a pas été trouvé, retournez une réponse 404
            return ResponseEntity.notFound().build();
        }
    }


}
