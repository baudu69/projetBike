package fr.polytech.projetapi.ressource;

import fr.polytech.projetapi.dto.SignUpRequest;
import fr.polytech.projetapi.model.Utilisateur;
import fr.polytech.projetapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    private final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PutMapping
    public ResponseEntity<Void> updateUtilisateur(Authentication authentication, @RequestBody SignUpRequest utilisateur) {
        logger.info("Mise à jour de l'utilisateur {}", utilisateur);
        utilisateurService.updateUtilisateur(authentication, utilisateur);
        return ResponseEntity.noContent().build();
    }
}
