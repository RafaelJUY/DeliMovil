package com.delimovil.backend.repositories;

import com.delimovil.backend.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
}
