package com.finalsdproject.user;

import com.finalsdproject.user.model.ERole;
import com.finalsdproject.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole role);
}
