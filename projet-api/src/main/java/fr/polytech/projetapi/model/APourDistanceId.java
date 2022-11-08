package fr.polytech.projetapi.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class APourDistanceId implements Serializable {
    private static final long serialVersionUID = -7813166795140019307L;
    @Column(name = "id_etape_dep", nullable = false)
    private Integer idEtapeDep;

    @Column(name = "id_etape_arr", nullable = false)
    private Integer idEtapeArr;

    public Integer getIdEtapeDep() {
        return idEtapeDep;
    }

    public void setIdEtapeDep(Integer idEtapeDep) {
        this.idEtapeDep = idEtapeDep;
    }

    public Integer getIdEtapeArr() {
        return idEtapeArr;
    }

    public void setIdEtapeArr(Integer idEtapeArr) {
        this.idEtapeArr = idEtapeArr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        APourDistanceId entity = (APourDistanceId) o;
        return Objects.equals(this.idEtapeArr, entity.idEtapeArr) &&
                Objects.equals(this.idEtapeDep, entity.idEtapeDep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEtapeArr, idEtapeDep);
    }

}