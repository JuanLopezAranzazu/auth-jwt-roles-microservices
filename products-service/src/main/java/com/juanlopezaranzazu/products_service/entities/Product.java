package com.juanlopezaranzazu.products_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data  // Genera automáticamente getters, setters, equals, hashCode y toString
@NoArgsConstructor  // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
@Entity  // Indica que esta clase es una entidad JPA
@Table(name = "products")  // Nombre de la tabla en la base de datos
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del producto

    @Column(nullable = false)
    private String name; // Nombre del producto

    @Column(nullable = false)
    private String description; // Descripción del producto

    @Column(nullable = false)
    private BigDecimal price; // Precio del producto

    @Column(nullable = false)
    private Integer stock; // Cantidad en stock del producto

    // Constructor con todos los atributos excepto id
    public Product(String name, String description, BigDecimal price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
