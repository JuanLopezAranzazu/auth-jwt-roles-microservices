package com.juanlopezaranzazu.products_service.repositories;

import com.juanlopezaranzazu.products_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}
