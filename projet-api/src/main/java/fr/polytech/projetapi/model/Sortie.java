package fr.polytech.projetapi.model;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sortie")
public class Sortie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "num_sortie", nullable = false)
    private Integer id;

    @Column(name = "num_util", nullable = false)
    private Integer numUtil;

    @Column(name = "date_sortie")
    private LocalDate dateSortie;

    @Column(name = "heure_depart")
    private LocalTime heureDepart;

    @Column(name = "heure_arrivee")
    private LocalTime heureArrivee;

    @Column(name = "lieu_depart", length = 30)
    private String lieuDepart;

    @Column(name = "distance_parcourue", precision = 6, scale = 2)
    private Double distanceParcourue;

    @OneToMany(mappedBy = "sortie", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("numEtape ASC")
    private List<Etape> etapes = new ArrayList<>();

    public List<Etape> getEtapes() {
        return etapes;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumUtil() {
        return numUtil;
    }

    public void setNumUtil(Integer numUtil) {
        this.numUtil = numUtil;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public Double getDistanceParcourue() {
        return distanceParcourue;
    }

    public void setDistanceParcourue(Double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

}