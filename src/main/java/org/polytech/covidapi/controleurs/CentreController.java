package org.polytech.covidapi.controleurs;
import java.util.List;
import org.polytech.covidapi.modele.Centre;
import org.polytech.covidapi.repositories.CentreRepository;
import org.polytech.covidapi.services.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CentreController {

    private final CentreService centreService;
    private final CentreRepository centreRepository;

    @Autowired
    public CentreController(CentreService centreService,CentreRepository centreRepository ) {
        this.centreService = centreService;
        this.centreRepository = centreRepository;
    }

    //Recherche des centres d'une ville choisie
    @GetMapping("/public/centres")
    public List<Centre> rechercherCentresParVille(@RequestParam String ville) {
        return centreService.rechercherCentresParVille(ville);
    }

    //Création d'un nouveau centre 
    @PostMapping("/superadmin/centres")
    @ResponseBody
    public Centre creerCentre(@RequestBody Centre centre) {
        return centreService.creerCentre(centre);
    }

    //Lécture d'un centre par l'Id
    @GetMapping("superadmin/centres/{id}")
    public Centre getCentreById(@PathVariable Long id) {
        return centreRepository.findById(id).orElse(null);
    }

    //Lécture de tous les centre presents dans la base de données
    @GetMapping("superadmin/centres")
    public List<Centre> getAllCentres() {
        return centreRepository.findAll();
    }

    //Update d'un nouveau centre par l'Id
    @PutMapping("superadmin/centres/{id}")
    public Centre updateCentre(@PathVariable Long id, @RequestBody Centre centre) {
        centre.setId(id);
        return centreRepository.save(centre);
    }

    //Effacement d'un centre par l'Id
    @DeleteMapping("superadmin/centres/{id}")
    public void deleteCentre(@PathVariable Long id) {
        centreRepository.deleteById(id);
    }

}


