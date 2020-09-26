package com.jhipster.nigeria.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.jhipster.nigeria.domain.enumeration.VehicleType;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "vin", nullable = false)
    private String vin;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VehicleType type;

    @ManyToOne
    @JsonIgnoreProperties(value = "vehicles", allowSetters = true)
    private Profile owner;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public Vehicle vin(String vin) {
        this.vin = vin;
        return this;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public VehicleType getType() {
        return type;
    }

    public Vehicle type(VehicleType type) {
        this.type = type;
        return this;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Profile getOwner() {
        return owner;
    }

    public Vehicle owner(Profile profile) {
        this.owner = profile;
        return this;
    }

    public void setOwner(Profile profile) {
        this.owner = profile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        return id != null && id.equals(((Vehicle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + getId() +
            ", vin='" + getVin() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
