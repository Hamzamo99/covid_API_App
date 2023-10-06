package org.polytech.covidapi.config;

import org.polytech.covidapi.modele.SuperAdmin;
import org.polytech.covidapi.repositories.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;



@Component
public class SuperAdminDataLoader {

    private final SuperAdminRepository superAdminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SuperAdminDataLoader(SuperAdminRepository superAdminRepository,PasswordEncoder passwordEncoder) {
        this.superAdminRepository = superAdminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void loadSuperAdmin() {
        if (superAdminRepository.findByNom("test") == null) {
            SuperAdmin superAdmin = new SuperAdmin();
            superAdmin.setNom("test");
            superAdmin.setPrenom("Doe");
            superAdmin.setEmail("johndoe@example.com");
            superAdmin.setTelephone("1234567890");
            
            String hashedPassword = passwordEncoder.encode("test");
            superAdmin.setPassword(hashedPassword);
            
            superAdminRepository.save(superAdmin);
        }
    }
}
