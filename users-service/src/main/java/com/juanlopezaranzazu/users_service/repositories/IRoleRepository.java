package com.juanlopezaranzazu.users_service.repositories;

import com.juanlopezaranzazu.users_service.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name); // Obtener un rol por su nombre
}
