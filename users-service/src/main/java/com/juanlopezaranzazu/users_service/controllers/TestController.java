package com.juanlopezaranzazu.users_service.controllers;

import com.juanlopezaranzazu.users_service.utils.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/user")
    public String getUserMessage() {
        // Validar roles de usuario
        SecurityUtil.validateRoles(List.of("ROLE_USER", "ROLE_ADMIN"));
        return "Mensaje para el usuario autenticado";
    }

    @GetMapping("/admin")
    public String getAdminMessage() {
        // Validar roles de administrador
        SecurityUtil.validateRoles(List.of("ROLE_ADMIN"));
        return "Mensaje para el administrador autenticado";
    }
}
