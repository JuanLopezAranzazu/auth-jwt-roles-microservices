package com.juanlopezaranzazu.products_service.controllers;

import com.juanlopezaranzazu.products_service.dtos.ProductRequest;
import com.juanlopezaranzazu.products_service.dtos.ProductResponse;
import com.juanlopezaranzazu.products_service.services.IProductService;
import com.juanlopezaranzazu.products_service.utils.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    // Obtener todos los productos
    // Ejemplo URL /api/v1/products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        SecurityUtil.validateRoles(List.of("ROLE_USER", "ROLE_ADMIN"));
        return ResponseEntity.ok(productService.findAll());
    }

    // Obtener productos con paginación
    // Ejemplo URL /api/v1/products/pageable?page=0&size=8
    @GetMapping("/pageable")
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        SecurityUtil.validateRoles(List.of("ROLE_USER", "ROLE_ADMIN"));
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    // Obtener un producto por su id
    // Ejemplo URL /api/v1/products/1
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        SecurityUtil.validateRoles(List.of("ROLE_USER", "ROLE_ADMIN"));
        return ResponseEntity.ok(productService.findById(id));
    }

    // Crear un producto
    // Ejemplo URL /api/v1/products
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        SecurityUtil.validateRoles(List.of("ROLE_ADMIN"));
        return ResponseEntity.ok(productService.save(productRequest));
    }

    // Editar un producto por su id
    // Ejemplo URL /api/v1/products/1
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest productRequest) {
        SecurityUtil.validateRoles(List.of("ROLE_ADMIN"));
        return ResponseEntity.ok(productService.update(id, productRequest));
    }

    // Eliminar un producto por su id
    // Ejemplo URL /api/v1/products/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        SecurityUtil.validateRoles(List.of("ROLE_ADMIN"));
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
