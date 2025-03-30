package com.juanlopezaranzazu.products_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id; // Identificador único del producto

    private String name; // Nombre del producto

    private String description; // Descripción del producto

    private BigDecimal price; // Precio del producto

    private Integer stock; // Cantidad de producto disponible en stock
}
