package fr.polytech.projetapi.model;

import javax.persistence.*;

@Entity
@Table(name = "vtt")
public class Vtt {
    @Id
    @Column(name = "id_velo", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_velo", nullable = false)
    private Velo velo;

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

}