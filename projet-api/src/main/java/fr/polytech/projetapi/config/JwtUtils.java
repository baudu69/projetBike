package fr.polytech.projetapi.config;

import fr.polytech.projetapi.model.Utilisateur;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    
    private final BikeConfig bikeConfig;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public JwtUtils(BikeConfig bikeConfig) {
        this.bikeConfig = bikeConfig;
    }

    public String generateJwtToken(Authentication authentication, Utilisateur user) {
        return Jwts.builder()
                .setSubject((user.getLogin()))
                .setIssuedAt(new Date())
                .addClaims(Map.of("user", user))
                .setExpiration(Date.from(LocalDateTime.now().plus(1, java.time.temporal.ChronoUnit.HOURS).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, this.bikeConfig.getSecret())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(this.bikeConfig.getSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.bikeConfig.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}


