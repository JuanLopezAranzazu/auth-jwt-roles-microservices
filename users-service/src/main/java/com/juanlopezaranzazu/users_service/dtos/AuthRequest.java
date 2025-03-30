package com.juanlopezaranzazu.users_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "El campo email no puede estar vacío")
    @Email(message = "El campo email no es un email válido")
    private String email; // Correo electrónico del usuario

    @NotEmpty(message = "El campo password no puede estar vacío")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, un número y un carácter especial")
    private String password; // Contraseña del usuario
}
