package fr.polytech.projetapi.model;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @Column(name = "id_velo", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_velo", nullable = false)
    private Velo velo;

    @Column(name = "type_cadre", length = 20)
    private String typeCadre;

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

    public String getTypeCadre() {
        return typeCadre;
    }

    public void setTypeCadre(String typeCadre) {
        this.typeCadre = typeCadre;
    }

}