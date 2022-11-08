package fr.polytech.projetapi.model;

import javax.persistence.*;

@Entity
@Table(name = "vae")
public class Vae {
    @Id
    @Column(name = "id_velo", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_velo", nullable = false)
    private Velo velo;

    @Column(name = "puissance_moteur")
    private Integer puissanceMoteur;

    @Column(name = "puissance_batterie")
    private Integer puissanceBatterie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Velo getVelo() {
        return velo;
    }

    public void setVelo(Velo velo) {
        this.velo = velo;
    }

    public Integer getPuissanceMoteur() {
        return puissanceMoteur;
    }

    public void setPuissanceMoteur(Integer puissanceMoteur) {
        this.puissanceMoteur = puissanceMoteur;
    }

    public Integer getPuissanceBatterie() {
        return puissanceBatterie;
    }

    public void setPuissanceBatterie(Integer puissanceBatterie) {
        this.puissanceBatterie = puissanceBatterie;
    }

}