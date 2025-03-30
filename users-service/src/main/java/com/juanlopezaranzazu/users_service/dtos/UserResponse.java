package com.juanlopezaranzazu.users_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id; // Identificador único del usuario
    private String name; // Nombre del usuario
    private String email; // Correo electrónico del usuario
    private String roleName; // Nombre del rol del usuario
}
