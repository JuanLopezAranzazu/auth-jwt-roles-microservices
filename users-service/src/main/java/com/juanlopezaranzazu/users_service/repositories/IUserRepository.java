package com.juanlopezaranzazu.users_service.repositories;

import com.juanlopezaranzazu.users_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Obtener un usuario por su correo electrónico
    boolean existsByEmail(String email); // Verificar si un correo electrónico ya existe
}
