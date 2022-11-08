package fr.polytech.projetapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "etape")
public class Etape {
    @Id
    @Column(name = "id_etape", nullable = false)
    private Integer id;

    @Column(name = "num_etape", nullable = false)
    private Integer numEtape;

    @Column(name = "nom_etape", length = 25)
    private String nomEtape;

    @Column(name = "latitude", precision = 16, scale = 14)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 16, scale = 14)
    private BigDecimal longitude;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_sortie")
    private Sortie numSortie;

    public Sortie getNumSortie() {
        return numSortie;
    }

    public void setNumSortie(Sortie numSortie) {
        this.numSortie = numSortie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumEtape() {
        return numEtape;
    }

    public void setNumEtape(Integer numEtape) {
        this.numEtape = numEtape;
    }

    public String getNomEtape() {
        return nomEtape;
    }

    public void setNomEtape(String nomEtape) {
        this.nomEtape = nomEtape;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

}