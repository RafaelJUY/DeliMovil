package com.delimovil.backend.repositories;

import com.delimovil.backend.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findOneByUsername(String username);
}
