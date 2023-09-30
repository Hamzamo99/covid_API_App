package org.polytech.covidapi.controleurs;
import java.util.List;
import org.polytech.covidapi.modele.AdministrateurCentre;
import org.polytech.covidapi.modele.Inscription;
import org.polytech.covidapi.repositories.AdministrateurCentreRepository;
import org.polytech.covidapi.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrateurCentreControlleur {
    
    
    private final AdministrateurCentreRepository administrateurCentreRepository;
    private final InscriptionRepository inscriptionRepository;
    
    @Autowired
    public AdministrateurCentreControlleur(AdministrateurCentreRepository administrateurCentreRepository, InscriptionRepository inscriptionRepository) {
        this.administrateurCentreRepository = administrateurCentreRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @PostMapping("api/superadmin/administrateurs")
    public AdministrateurCentre createAdministrateurCentre(@RequestBody AdministrateurCentre administrateurCentre) {
        //Code pour créer un nouvel administrateur du centre et le sauvegarder en base de données
        return administrateurCentreRepository.save(administrateurCentre);
    }

    @GetMapping("api/superadmin/administrateurs/{id}")
    public AdministrateurCentre getAdministrateurCentreById(@PathVariable Long id) {
        return administrateurCentreRepository.findById(id).orElse(null);
    }

    @GetMapping("api/superadmin/administrateurs/centre/{centreId}")
    public List<AdministrateurCentre> getAdministrateursByCentreId(@PathVariable Long centreId) {
        return administrateurCentreRepository.findAdminsByCentreId(centreId);
    }

    @PutMapping("api/superadmin/administrateurs/{id}")
        public AdministrateurCentre updateAdministrateurCentre(@PathVariable Long id, @RequestBody AdministrateurCentre administrateurCentre) {
        //Code pour mettre à jour l'administrateur du centre avec l'ID spécifié
        return administrateurCentreRepository.save(administrateurCentre);
    }

    @DeleteMapping("api/superadmin/administrateurs/{id}")
    public void deleteAdministrateurCentre(@PathVariable Long id) {
        //Code pour supprimer l'administrateur du centre avec l'ID spécifié
        administrateurCentreRepository.deleteById(id);
    }

    //Lécture de tous les administrateurs presents dans la base de données
    @GetMapping("api/superadmin/administrateurs")
    public List<AdministrateurCentre> getAllAdministrators() {
        return administrateurCentreRepository.findAll();
    }

    @GetMapping("api/admin/inscriptions")
    public List<Inscription> getInscriptionsByCentre(@RequestParam Long centreId) {
        //Code pour récupérer la liste des réservations effectuées pour le centre spécifié
        return inscriptionRepository.findByCentreId(centreId);
    }

    @DeleteMapping("api/admin/inscriptions/{id}")
    public void deleteInscription(@PathVariable Long id) {
        //Code pour supprimer l'inscription avec l'ID spécifié
        inscriptionRepository.deleteById(id);
    }

}
