package org.polytech.covidapi.controleurs;
import java.util.List;
import org.polytech.covidapi.modele.Centre;
import org.polytech.covidapi.services.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CentreController {

    private final CentreService centreService;
    

    @Autowired
    public CentreController(CentreService centreService) {
        this.centreService = centreService;
    }

    //Recherche des centres d'une ville choisie (OK)
    @GetMapping("api/public/centres")
    public List<Centre> rechercherCentresParVille(@RequestParam String ville) {
        return centreService.rechercherCentresParVille(ville);
    }
   

}


