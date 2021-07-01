package com.finalsdproject.animal;

import com.finalsdproject.animal.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
