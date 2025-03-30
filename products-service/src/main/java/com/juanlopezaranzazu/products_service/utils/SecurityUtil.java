package com.juanlopezaranzazu.products_service.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityUtil {

    // Obtener el userId del contexto de seguridad
    public static Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("No hay usuario autenticado.");
        }
        return (Long) authentication.getPrincipal();
    }

    // Obtener los roles del usuario autenticado
    public static List<String> getAuthenticatedUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities() == null) {
            throw new RuntimeException("No hay usuario autenticado.");
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    // Validar si el usuario tiene al menos uno de los roles requeridos
    public static void validateRoles(List<String> requiredRoles) {
        List<String> userRoles = getAuthenticatedUserRoles();

        boolean hasRequiredRole = requiredRoles.stream().anyMatch(userRoles::contains);
        if (!hasRequiredRole) {
            throw new RuntimeException("Acceso denegado. No tienes los permisos necesarios.");
        }
    }
}