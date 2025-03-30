package com.juanlopezaranzazu.users_service.services;

import com.juanlopezaranzazu.users_service.dtos.AuthRequest;
import com.juanlopezaranzazu.users_service.dtos.AuthResponse;
import com.juanlopezaranzazu.users_service.dtos.UserRequest;
import com.juanlopezaranzazu.users_service.dtos.UserResponse;
import com.juanlopezaranzazu.users_service.entities.Role;
import com.juanlopezaranzazu.users_service.entities.User;
import com.juanlopezaranzazu.users_service.repositories.IRoleRepository;
import com.juanlopezaranzazu.users_service.repositories.IUserRepository;
import com.juanlopezaranzazu.users_service.utils.JwtUtil;
import com.juanlopezaranzazu.users_service.utils.SecurityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(AuthRequest authRequest) {
        try {
            // Autenticar al usuario con el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            // Obtener el usuario autenticado
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            // Obtener el ID del usuario y los roles
            Long userId = user.getId();
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            // Generar el token JWT
            String token = jwtUtil.generateToken(email, userId, roles);
            return new AuthResponse(token);

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Usuario o contraseña incorrectos.");
        }
    }

    @Override
    @Transactional
    public UserResponse register(UserRequest userRequest) {
        // Verificar si el usuario ya existe
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Obtener el rol de usuario por defecto
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

        // Crear un nuevo usuario
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(role);

        // Guardar el usuario en la base de datos
        User savedUser = userRepository.save(user);

        return convertToUserResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getAuthenticatedUser() {
        // Obtener el usuario autenticado desde el contexto de seguridad
        Long userId = SecurityUtil.getAuthenticatedUserId();
        // Obtener el usuario desde la base de datos
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        return convertToUserResponse(user);
    }

    // Función para convertir un User a UserResponse
    private UserResponse convertToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().getName()
        );
    }
}
