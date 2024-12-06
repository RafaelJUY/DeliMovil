package com.delimovil.backend.repositories;

import com.delimovil.backend.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findOneByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
