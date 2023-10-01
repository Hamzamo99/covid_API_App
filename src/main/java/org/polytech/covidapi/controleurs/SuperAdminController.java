package org.polytech.covidapi.controleurs;
import java.util.List;
import java.util.Optional;
import org.polytech.covidapi.modele.SuperAdmin;
import org.polytech.covidapi.repositories.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SuperAdminController {

    @Autowired
    private SuperAdminRepository superAdminRepository;

    // Opération de création (Create)
    @PostMapping("api/superadmins")
    public SuperAdmin createSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        return superAdminRepository.save(superAdmin);
    }

    // Opération de lecture (Read) par ID
    @GetMapping("api/superadmins/{id}")
    public ResponseEntity<SuperAdmin> getSuperAdminById(@PathVariable Long id) {
        Optional<SuperAdmin> superAdmin = superAdminRepository.findById(id);
        if (superAdmin.isPresent()) {
            return ResponseEntity.ok(superAdmin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Opération de mise à jour (Update) par ID
    @PutMapping("api/superadmins/{id}")
    public ResponseEntity<SuperAdmin> updateSuperAdmin(@PathVariable Long id, @RequestBody SuperAdmin updatedSuperAdmin) {
        if (!superAdminRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedSuperAdmin.setId(id);
        SuperAdmin savedSuperAdmin = superAdminRepository.save(updatedSuperAdmin);
        return ResponseEntity.ok(savedSuperAdmin);
    }

    // Opération de suppression (Delete) par ID
    @DeleteMapping("api/superadmins/{id}")
    public ResponseEntity<Void> deleteSuperAdmin(@PathVariable Long id) {
        if (!superAdminRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        superAdminRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Opération de liste (Read) de tous les SuperAdmins
    @GetMapping("api/superadmins")
    public List<SuperAdmin> getAllSuperAdmins() {
        return superAdminRepository.findAll();
    }
}

