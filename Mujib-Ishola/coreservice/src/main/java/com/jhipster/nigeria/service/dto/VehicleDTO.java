package com.jhipster.nigeria.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.jhipster.nigeria.domain.enumeration.VehicleType;

/**
 * A DTO for the {@link com.jhipster.nigeria.domain.Vehicle} entity.
 */
public class VehicleDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String vin;

    private VehicleType type;


    private Long ownerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long profileId) {
        this.ownerId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VehicleDTO)) {
            return false;
        }

        return id != null && id.equals(((VehicleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VehicleDTO{" +
            "id=" + getId() +
            ", vin='" + getVin() + "'" +
            ", type='" + getType() + "'" +
            ", ownerId=" + getOwnerId() +
            "}";
    }
}
