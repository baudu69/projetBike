package fr.polytech.projetapi.ressource;

import fr.polytech.projetapi.config.JwtUtils;
import fr.polytech.projetapi.dto.JwtResponse;
import fr.polytech.projetapi.dto.LoginRequest;
import fr.polytech.projetapi.dto.SignUpRequest;
import fr.polytech.projetapi.model.Utilisateur;
import fr.polytech.projetapi.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        Utilisateur user = userDetailsService.loadFullUserByUsername(loginRequest.username());
        String jwt = jwtUtils.generateJwtToken(authentication, user);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        this.userDetailsService.signUp(signUpRequest);
        return ResponseEntity.noContent().build();
    }

}
