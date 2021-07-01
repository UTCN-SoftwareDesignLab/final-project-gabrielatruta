package com.finalsdproject.animal;

import com.finalsdproject.animal.model.Animal;
import com.finalsdproject.animal.model.AnimalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.finalsdproject.animal.model.ESpecies.DOG;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final SimpMessageSendingOperations webSock;

    public List<AnimalDTO> allAnimals() {
        List<Animal> availableAnimals = new ArrayList<>();
        List<Animal> allAnimals = animalRepository.findAll();
        for (Animal animal: allAnimals) {
            if (animal.getOwner() == null)
                availableAnimals.add(animal);
        }
        return availableAnimals
                .stream().map(animalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> adoptedAnimals() {
        List<Animal> ownedAnimals = new ArrayList<>();
        List<Animal> allAnimals = animalRepository.findAll();
        for (Animal animal: allAnimals) {
            if (animal.getOwner() != null)
                ownedAnimals.add(animal);
        }
        return ownedAnimals
                .stream().map(animalMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Animal findById(Long id){
        return animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with id: " + id));
    }

    public AnimalDTO create(AnimalDTO animalDTO) {

        webSock.convertAndSend("/adoptionRequest", animalDTO.getName() + " is available for adoption! Go and check it out! :)");

        return animalMapper.toDTO(animalRepository.save(animalMapper.fromDTO(animalDTO)));
    }

    public AnimalDTO edit(Long id, AnimalDTO animalDTO) {
        Animal actAnimal = findById(id);
        actAnimal.setAnimal(DOG);
        actAnimal.setName(animalDTO.getName());
        actAnimal.setAge(animalDTO.getAge());
        actAnimal.setGender(animalDTO.getGender());
        actAnimal.setSize(animalDTO.getSize());
        actAnimal.setDescription(animalDTO.getDescription());
        return animalMapper.toDTO(
                animalRepository.save(actAnimal)
        );
    }

    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }

    public AnimalDTO get(Long id) {
        return  animalMapper.toDTO(findById(id));
    }
}
