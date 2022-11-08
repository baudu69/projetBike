package fr.polytech.projetapi.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class AcheteId implements Serializable {
    private static final long serialVersionUID = -4855953594419035474L;
    @Column(name = "id_velo", nullable = false)
    private Integer idVelo;

    @Column(name = "num_util", nullable = false)
    private Integer numUtil;

    @Column(name = "date_jour", nullable = false)
    private LocalDate dateJour;

    public Integer getIdVelo() {
        return idVelo;
    }

    public void setIdVelo(Integer idVelo) {
        this.idVelo = idVelo;
    }

    public Integer getNumUtil() {
        return numUtil;
    }

    public void setNumUtil(Integer numUtil) {
        this.numUtil = numUtil;
    }

    public LocalDate getDateJour() {
        return dateJour;
    }

    public void setDateJour(LocalDate dateJour) {
        this.dateJour = dateJour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AcheteId entity = (AcheteId) o;
        return Objects.equals(this.numUtil, entity.numUtil) &&
                Objects.equals(this.dateJour, entity.dateJour) &&
                Objects.equals(this.idVelo, entity.idVelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numUtil, dateJour, idVelo);
    }

}