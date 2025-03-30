package com.juanlopezaranzazu.users_service.controllers;

import com.juanlopezaranzazu.users_service.dtos.AuthRequest;
import com.juanlopezaranzazu.users_service.dtos.AuthResponse;
import com.juanlopezaranzazu.users_service.dtos.UserRequest;
import com.juanlopezaranzazu.users_service.dtos.UserResponse;
import com.juanlopezaranzazu.users_service.services.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService){
        this.authService = authService;
    }

    // Iniciar sesión con el correo y la contraseña
    // Ejemplo URL /api/v1/auth/login
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    // Registrar un nuevo usuario
    // Ejemplo URL /api/v1/auth/register
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authService.register(userRequest));
    }

    // Obtener el usuario autenticado
    // Ejemplo URL /api/v1/auth/user
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> getAuthenticatedUser() {
        return ResponseEntity.ok(authService.getAuthenticatedUser());
    }
}
