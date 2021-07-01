package com.finalsdproject.animal;

import com.finalsdproject.animal.model.Animal;
import com.finalsdproject.animal.model.AnimalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    AnimalDTO toDTO(Animal animal);
    Animal fromDTO(AnimalDTO animalDTO);

}
