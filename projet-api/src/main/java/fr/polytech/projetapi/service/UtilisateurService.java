package fr.polytech.projetapi.service;

import fr.polytech.projetapi.dto.SignUpRequest;
import fr.polytech.projetapi.model.Utilisateur;
import fr.polytech.projetapi.repository.UtilisateurRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Utilisateur> findByLogin(String login) {
        return utilisateurRepository.findByLogin(login);
    }

    public void updateUtilisateur(Authentication authentication, SignUpRequest utilisateur) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Utilisateur base = this.utilisateurRepository.findByLogin(userDetails.getUsername()).orElseThrow(IllegalStateException::new);
        base.setNomUtil(utilisateur.lastname());
        base.setPrenomUtil(utilisateur.firstname());
        base.setDateNaissance(utilisateur.birthdate());
        base.setTaille(utilisateur.taille());
        base.setPoids(utilisateur.poids());
        if (utilisateur.password() != null && !StringUtils.isEmpty(utilisateur.password())) {
            base.setPassword(passwordEncoder.encode(utilisateur.password()));
        }
        this.utilisateurRepository.save(base);
    }
}
