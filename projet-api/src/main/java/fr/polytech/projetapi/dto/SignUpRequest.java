package fr.polytech.projetapi.dto;

import java.io.Serializable;

public record SignUpRequest(String lastname, String firstname, String username, String password, double poids, double taille) implements Serializable {
}
