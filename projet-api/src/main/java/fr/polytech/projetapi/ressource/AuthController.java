package fr.polytech.projetapi.ressource;

import fr.polytech.projetapi.config.JwtUtils;
import fr.polytech.projetapi.dto.JwtResponse;
import fr.polytech.projetapi.dto.LoginRequest;
import fr.polytech.projetapi.dto.SignUpRequest;
import fr.polytech.projetapi.model.Utilisateur;
import fr.polytech.projetapi.service.UserDetailsServiceImpl;
import fr.polytech.projetapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final UtilisateurService utilisateurService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService, UtilisateurService utilisateurService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Authentification de l'utilisateur {}", loginRequest.username());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        String jwt = jwtUtils.generateJwtToken(authentication);
        Utilisateur utilisateur = this.utilisateurService.findByLogin(loginRequest.username()).orElseThrow(IllegalStateException::new);
        return ResponseEntity.ok(new JwtResponse(jwt,
                utilisateur));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        logger.info("Inscription de l'utilisateur {}", signUpRequest.username());
        this.userDetailsService.signUp(signUpRequest);
        return ResponseEntity.noContent().build();
    }

}
