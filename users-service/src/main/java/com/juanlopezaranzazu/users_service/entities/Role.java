package com.juanlopezaranzazu.users_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data  // Genera automáticamente getters, setters, equals, hashCode y toString
@NoArgsConstructor  // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
@Entity  // Indica que esta clase es una entidad JPA
@Table(name = "roles")  // Nombre de la tabla en la base de datos
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del rol

    @Column(nullable = false, unique = true)
    private String name; // Nombre del rol

    @Column(nullable = false)
    private String description; // Descripción del rol

    // Relación con la entidad User
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users; // Lista de usuarios asociados a este rol

    // Constructor con todos los atributos excepto id
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
