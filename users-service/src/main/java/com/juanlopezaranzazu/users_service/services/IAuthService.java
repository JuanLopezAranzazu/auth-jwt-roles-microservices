package com.juanlopezaranzazu.users_service.services;

import com.juanlopezaranzazu.users_service.dtos.AuthRequest;
import com.juanlopezaranzazu.users_service.dtos.UserRequest;
import com.juanlopezaranzazu.users_service.dtos.AuthResponse;
import com.juanlopezaranzazu.users_service.dtos.UserResponse;

public interface IAuthService {
    AuthResponse login(AuthRequest authRequest); // Obtener el token JWT al iniciar sesión
    UserResponse register(UserRequest userRequest); // Registrar un nuevo usuario
    UserResponse getAuthenticatedUser(); // Obtener el usuario autenticado
}
