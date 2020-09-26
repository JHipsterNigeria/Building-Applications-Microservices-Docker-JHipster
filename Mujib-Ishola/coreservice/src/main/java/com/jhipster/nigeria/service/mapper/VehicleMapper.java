package com.jhipster.nigeria.service.mapper;


import com.jhipster.nigeria.domain.*;
import com.jhipster.nigeria.service.dto.VehicleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vehicle} and its DTO {@link VehicleDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface VehicleMapper extends EntityMapper<VehicleDTO, Vehicle> {

    @Mapping(source = "owner.id", target = "ownerId")
    VehicleDTO toDto(Vehicle vehicle);

    @Mapping(source = "ownerId", target = "owner")
    Vehicle toEntity(VehicleDTO vehicleDTO);

    default Vehicle fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        return vehicle;
    }
}
