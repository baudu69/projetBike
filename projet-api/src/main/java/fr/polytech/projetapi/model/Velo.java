package fr.polytech.projetapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "velo")
public class Velo {
    @Id
    @Column(name = "id_velo", nullable = false)
    private Integer id;

    @Column(name = "nom_velo", length = 20)
    private String nomVelo;

    @Column(name = "roues", precision = 6, scale = 2)
    private BigDecimal roues;

    @Column(name = "casette", length = 10)
    private String casette;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomVelo() {
        return nomVelo;
    }

    public void setNomVelo(String nomVelo) {
        this.nomVelo = nomVelo;
    }

    public BigDecimal getRoues() {
        return roues;
    }

    public void setRoues(BigDecimal roues) {
        this.roues = roues;
    }

    public String getCasette() {
        return casette;
    }

    public void setCasette(String casette) {
        this.casette = casette;
    }

}