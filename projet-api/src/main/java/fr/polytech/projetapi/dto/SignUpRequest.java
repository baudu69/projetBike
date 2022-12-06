package fr.polytech.projetapi.dto;

import java.io.Serializable;
import java.time.LocalDate;

//Sert aussi pour la mise a jour d'un utilisateur (mais sans le login)
public record SignUpRequest(String lastname, String firstname, String username, String password, double poids, double taille, LocalDate birthdate) implements Serializable {
    @Override
    public String toString() {
        return username;
    }
}
