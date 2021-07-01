package com.finalsdproject.owner;

import com.finalsdproject.owner.model.Owner;
import com.finalsdproject.owner.model.OwnerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDTO toDTO(Owner owner);
    Owner fromDTO(OwnerDTO ownerDTO);
}
