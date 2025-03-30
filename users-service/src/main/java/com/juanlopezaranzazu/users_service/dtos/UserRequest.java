package com.juanlopezaranzazu.users_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotEmpty(message = "El campo name no puede estar vacío")
    @Size(min = 3, max = 50, message = "El campo name debe tener entre 3 y 50 caracteres")
    private String name;

    @NotEmpty(message = "El campo email no puede estar vacío")
    @Email(message = "El campo email no es un email válido")
    private String email;

    @NotEmpty(message = "El campo password no puede estar vacío")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, un número y un carácter especial")
    private String password;
}
