package fr.polytech.projetapi.dto;

import fr.polytech.projetapi.model.Utilisateur;

public record JwtResponse(String jwt, Utilisateur user) {
}
