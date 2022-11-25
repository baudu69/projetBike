package fr.polytech.projetapi.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_util", nullable = false)
    private Integer id;

    @Column(name = "nom_util", length = 20)
    private String nomUtil;

    @Column(name = "prenom_util", length = 20)
    private String prenomUtil;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "taille", precision = 6, scale = 2)
    private double taille;

    @Column(name = "poids", precision = 6, scale = 2)
    private double poids;

    @Column(name = "login", length = 255)
    private String login;

    @Column(name = "password", length = 255)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getPrenomUtil() {
        return prenomUtil;
    }

    public void setPrenomUtil(String prenomUtil) {
        this.prenomUtil = prenomUtil;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}