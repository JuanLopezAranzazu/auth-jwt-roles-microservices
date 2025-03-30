package com.juanlopezaranzazu.products_service.services;

import com.juanlopezaranzazu.products_service.dtos.ProductRequest;
import com.juanlopezaranzazu.products_service.dtos.ProductResponse;
import com.juanlopezaranzazu.products_service.entities.Product;
import com.juanlopezaranzazu.products_service.repositories.IProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        // Obtener todos los productos
        return productRepository.findAll().stream()
                // Convertir cada producto a ProductResponse
                .map(this::convertToProductResponse)
                // Recoger los resultados en una lista
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(Pageable pageable) {
        // Obtener todos los productos paginados
        return productRepository.findAll(pageable)
                // Convertir cada producto a ProductResponse
                .map(this::convertToProductResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        // Buscar el producto por ID
        return productRepository.findById(id)
                // Si se encuentra, convertirlo a ProductResponse
                .map(this::convertToProductResponse)
                // Si no se encuentra, lanzar una excepción
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    @Transactional
    public ProductResponse save(ProductRequest productRequest) {
        // Crear un nuevo producto a partir de la solicitud
        Product product = new Product(
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getStock()
        );
        // Guardar el producto en la base de datos
        Product savedProduct = productRepository.save(product);
        // Convertir el producto guardado a ProductResponse y devolverlo
        return convertToProductResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest productRequest) {
        // Buscar el producto por ID
        Product product = productRepository.findById(id)
                // Si se encuentra, actualizarlo
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        // Actualizar los atributos del producto
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        // Guardar el producto actualizado en la base de datos
        Product updatedProduct = productRepository.save(product);
        // Convertir el producto actualizado a ProductResponse y devolverlo
        return convertToProductResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Buscar el producto por ID
        Product product = productRepository.findById(id)
                // Si se encuentra, eliminarlo
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        // Eliminar el producto de la base de datos
        productRepository.delete(product);
    }

    // Función para convertir un Product a ProductResponse
    private ProductResponse convertToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}
