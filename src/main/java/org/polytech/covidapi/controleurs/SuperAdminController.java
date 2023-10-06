package org.polytech.covidapi.controleurs;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.polytech.covidapi.modele.Administrateur;
import org.polytech.covidapi.modele.Centre;
import org.polytech.covidapi.modele.SuperAdmin;
import org.polytech.covidapi.repositories.AdministrateurRepository;
import org.polytech.covidapi.repositories.CentreRepository;
import org.polytech.covidapi.repositories.SuperAdminRepository;
import org.polytech.covidapi.services.CentreService;
import org.polytech.covidapi.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SuperAdminController {

    private SuperAdminRepository superAdminRepository;
    private CentreRepository centreRepository;
    private CentreService centreService;
    private AdministrateurRepository administrateurRepository;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SuperAdminController (SuperAdminRepository superAdminRepository,CentreRepository centreRepository,AdministrateurRepository administrateurRepository,CentreService centreService,CustomUserDetailsService customUserDetailsService) {
        this.administrateurRepository = administrateurRepository;
        this.centreRepository = centreRepository;
        this.centreService = centreService;
        this.superAdminRepository = superAdminRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    // Opération de création d'un SuperAdmin (Create) (OK)
    @PostMapping("api/admin/superadmin")
    public SuperAdmin createSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        return superAdminRepository.save(superAdmin);
    }

    // Opération de lecture d'un SuperAdmin (Read) par ID (OK)
    @GetMapping("api/admin/superadmin/{id}")
    public ResponseEntity<SuperAdmin> getSuperAdminById(@PathVariable Long id) {
        Optional<SuperAdmin> superAdmin = superAdminRepository.findById(id);
        if (superAdmin.isPresent()) {
            return ResponseEntity.ok(superAdmin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Opération de mise à jour d'un SuperAdmin (Update) par ID (OK)
    @PutMapping("api/admin/superadmin/{id}")
    public ResponseEntity<SuperAdmin> updateSuperAdmin(@PathVariable Long id, @RequestBody SuperAdmin updatedSuperAdmin) {
        if (!superAdminRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedSuperAdmin.setId(id);
        SuperAdmin savedSuperAdmin = superAdminRepository.save(updatedSuperAdmin);
        return ResponseEntity.ok(savedSuperAdmin);
    }

    // Opération de suppression d'un SuperAdmin (Delete) par ID (OK)
    @DeleteMapping("api/admin/superadmin/{id}")
    public ResponseEntity<Void> deleteSuperAdmin(@PathVariable Long id) {
        if (!superAdminRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        superAdminRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Opération de liste (Read) de tous les SuperAdmins (OK)
    @GetMapping("api/admin/superadmin")
    public List<SuperAdmin> getAllSuperAdmins() {
        return superAdminRepository.findAll();
    }

   @GetMapping("/api/login/superadmin")
    public UserDetails getInfosSuperAdmin(Principal principal) {

        String nom = principal.getName();
        UserDetails User = customUserDetailsService.loadUserByUsername(nom);
        if (User != null) {
            return User;
        } else {
            // Gérez le cas où l'utilisateur n'est pas trouvé
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + nom);
        }
    }


    //Création d'un nouveau centre par un SuperAdmin (OK)
    @PostMapping("api/admin/superadmin/centre") 
    @ResponseBody
    public Centre creerCentre(@RequestBody Centre centre) {
        return centreService.creerCentre(centre);
    }

    //Lécture d'un centre par l'Id par un SuperAdmin (OK)
    @GetMapping("api/admin/superadmin/centre/{id}")
    public Centre getCentreById(@PathVariable Long id) {
        return centreRepository.findById(id).orElse(null);
    }

    //Lécture de tous les centre presents dans la base de données par un SuperAdmin (OK)
    @GetMapping("api/admin/superadmin/centre")
    public List<Centre> getAllCentres() {
        return centreRepository.findAll();
    }

    //Update d'un nouveau centre par l'Id par un SuperAdmin (OK)
    @PutMapping("api/admin/superadmin/centre/{id}")
    public Centre updateCentre(@PathVariable Long id, @RequestBody Centre centre) {
        centre.setId(id);
        return centreRepository.save(centre);
    }

    //Effacement d'un centre par l'Id par un SuperAdmin (OK)
    @DeleteMapping("api/admin/superadmin/centre/{id}")
    public void deleteCentre(@PathVariable Long id) {
        centreRepository.deleteById(id);
    }

    //Code pour créer un nouvel administrateur du centre et le sauvegarder en base de données (OK)
    @PostMapping("api/admin/superadmin/administrateur")
    public Administrateur createAdministrateurCentre(@RequestBody Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }
    //(OK)
    @GetMapping("api/admin/superadmin/administrateur/{id}")
    public Administrateur getAdministrateurCentreById(@PathVariable Long id) {
        return administrateurRepository.findById(id).orElse(null);
    }
    //(OK)
    @GetMapping("api/admin/superadmin/administrateur/centre/{centreId}")
    public List<Administrateur> getAdministrateursByCentreId(@PathVariable Long centreId) {
        return administrateurRepository.findAdminsByCentreId(centreId);
    }
    //(OK)
    @PutMapping("api/admin/superadmin/administrateur/{id}")
    public ResponseEntity<Administrateur> updateAdministrateurCentre(@PathVariable Long id, @RequestBody Administrateur administrateur) {
        Optional<Administrateur> adminTrouveOptional = administrateurRepository.findById(id);

        if (adminTrouveOptional.isPresent()) {
            Administrateur adminTrouve = adminTrouveOptional.get();

            // Mettez à jour les champs de l'administrateur avec les nouvelles valeurs
            adminTrouve.setNom(administrateur.getNom());
            adminTrouve.setPrenom(administrateur.getPrenom());
            adminTrouve.setEmail(administrateur.getEmail());
            adminTrouve.setTelephone(administrateur.getTelephone());
            adminTrouve.setCentre(administrateur.getCentre());

            // Sauvegardez les modifications dans la base de données
            Administrateur updatedAdmin = administrateurRepository.save(adminTrouve);

            return ResponseEntity.ok(updatedAdmin);
        } else {
            // Si l'administrateur avec l'ID spécifié n'a pas été trouvé, retournez une réponse 404
            return ResponseEntity.notFound().build();
        }
    }

    //Code pour supprimer l'administrateur du centre avec l'ID spécifié (OK)
    @DeleteMapping("api/admin/superadmin/administrateur/{id}")
    public void deleteAdministrateurCentre(@PathVariable Long id) {
        administrateurRepository.deleteById(id);
    }

    //Lécture de tous les administrateurs presents dans la base de données (OK)
    @GetMapping("api/admin/superadmin/administrateur")
    public List<Administrateur> getAllAdministrators() {
        return administrateurRepository.findAll();
    }
}

