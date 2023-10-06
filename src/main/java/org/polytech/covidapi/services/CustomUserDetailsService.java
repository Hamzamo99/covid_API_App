package org.polytech.covidapi.services;
import java.util.Collections;
import java.util.List;

import org.polytech.covidapi.modele.SuperAdmin;
import org.polytech.covidapi.repositories.SuperAdminRepository;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SuperAdminRepository superAdminRepository;

    public CustomUserDetailsService(SuperAdminRepository superAdminRepository) {
        this.superAdminRepository = superAdminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
        SuperAdmin superAdmin = superAdminRepository.findByNom(nom);
        if (superAdmin == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + nom);
        }
        List<GrantedAuthority> ruth = List.of(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                
                return "ADMIN";
            }
        });
        return new User(superAdmin.getNom(), superAdmin.getPassword(), ruth);
    }
}
