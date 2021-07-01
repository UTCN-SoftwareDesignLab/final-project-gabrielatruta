package com.finalsdproject.owner;

import com.finalsdproject.animal.AnimalRepository;
import com.finalsdproject.animal.model.Animal;
import com.finalsdproject.owner.model.Owner;
import com.finalsdproject.owner.model.OwnerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final AnimalRepository animalRepository;

    public List<OwnerDTO> findAll() {
        return ownerRepository.findAll()
                .stream().map(ownerMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Owner findById (Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + id));

    }

    public OwnerDTO create(OwnerDTO ownerDTO, Long animalID) {
        Animal animal = animalRepository.findById(animalID)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with id: " + animalID));

        Owner owner = ownerMapper.fromDTO(ownerDTO);

        Owner actOwner = ownerRepository.save(ownerMapper.fromDTO(ownerDTO));
        animal.setOwner(actOwner);
        animalRepository.save(animal);

        Owner finalOwner = ownerRepository.findById(actOwner.getId())
                .orElseThrow(()-> new EntityNotFoundException("User not found with id = " + actOwner.getId()));

        return ownerMapper.toDTO(finalOwner);
    }

    public OwnerDTO edit(Long id, OwnerDTO ownerDTO){
        List<Long> ids = new ArrayList<>();
        for(Owner owner: ownerRepository.findAll()) {
               ids.add(owner.getId());
        }
        List<Animal> animals = animalRepository.findAllById(ids);
        Set<Animal> ownedAnimals = new HashSet<>(animals);
        Owner actOwner = findById(id);
        actOwner.setName(ownerDTO.getName());
        actOwner.setAddress(ownerDTO.getAddress());
        actOwner.setCNP(ownerDTO.getCNP());
        actOwner.setNumericalCode(ownerDTO.getNumericalCode());
        actOwner.setPhone(ownerDTO.getPhone());
        actOwner.setAnimals(ownedAnimals);

        return ownerMapper.toDTO(
                ownerRepository.save(actOwner)
        );
    }

    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    public OwnerDTO get (Long id) {
        return ownerMapper.toDTO(findById(id));
    }
}
