package fr.polytech.projetapi.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "a_pour_distance")
public class APourDistance {
    @EmbeddedId
    private APourDistanceId id;

    @MapsId("idEtapeDep")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etape_dep", nullable = false)
    private Etape idEtapeDep;

    @MapsId("idEtapeArr")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etape_arr", nullable = false)
    private Etape idEtapeArr;

    @Column(name = "nbr_km", precision = 6, scale = 2)
    private BigDecimal nbrKm;

    public APourDistanceId getId() {
        return id;
    }

    public void setId(APourDistanceId id) {
        this.id = id;
    }

    public Etape getIdEtapeDep() {
        return idEtapeDep;
    }

    public void setIdEtapeDep(Etape idEtapeDep) {
        this.idEtapeDep = idEtapeDep;
    }

    public Etape getIdEtapeArr() {
        return idEtapeArr;
    }

    public void setIdEtapeArr(Etape idEtapeArr) {
        this.idEtapeArr = idEtapeArr;
    }

    public BigDecimal getNbrKm() {
        return nbrKm;
    }

    public void setNbrKm(BigDecimal nbrKm) {
        this.nbrKm = nbrKm;
    }

}