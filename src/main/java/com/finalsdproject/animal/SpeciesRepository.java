package com.finalsdproject.animal;

import com.finalsdproject.animal.model.ESpecies;
import com.finalsdproject.animal.model.Species;
import com.finalsdproject.user.model.ERole;
import com.finalsdproject.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    Optional<Role> findByName(ESpecies species);

}
